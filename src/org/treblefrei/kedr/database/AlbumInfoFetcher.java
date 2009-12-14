package org.treblefrei.kedr.database;

import org.musicbrainz.JMBWSException;
import org.treblefrei.kedr.database.musicbrainz.MusicBrainz;
import org.treblefrei.kedr.database.musicdns.PuidFetcher;
import org.treblefrei.kedr.model.Album;
import org.treblefrei.kedr.model.Track;

import java.io.IOException;
import java.util.*;

public class AlbumInfoFetcher {

	public static Album fetchAlbumInfo(Album album) throws IOException {
        Set<Puid> allPuids = new HashSet<Puid>();

        try {
            PuidFetcher.fetchPuids(album);
        } catch (Exception e) {
            throw new IOException("can't fetch puids");
        }
//	    for (Set<Puid> puidSet : puids.values()) {
//            allPuids.addAll(puidSet);
//        }

        // ask musicbrainz for intersection of albums of tracks returned by puid
        // ask musicbrainz for album by returned id
//        Set<Album> albums = MusicBrainz.getAlbumsByPuids(allPuids);  // TODO "offer album saving" (tm)
                                                                    // Or, maybe, we would just choose the first one?
                                                                    // guess many albums would be very rare
        Set<Puid> puids = new HashSet<Puid>();
        Map<String, Track> trackByPuid = new HashMap<String, Track>();

        for (Track track : album.getTracks()) {
            List<Puid> puidList = track.getPuids();
            if (0 == puidList.size()) {
                continue; // skip tracks with unknown puids
            }
            Puid puid = puidList.get(0);
            puids.add(puid);
            trackByPuid.put(puid.toString(), track);
        }

//        System.out.println("fetcher: " + trackByPuid);

        Album newAlbum = null;
        try {
            newAlbum = MusicBrainz.getAlbumsByPuids(puids).iterator().next();
        } catch (NoSuchElementException e) {
            throw new IOException("album not found");    
        } catch (JMBWSException e) {
            throw new IOException("can't get info from musicbrainz");
        }

        for (Track track : newAlbum.getTracks()) {
//            System.out.println("fetcher: " + track.getPuids());
            String filePath = "unknown file";
            String format = null;
            Track old = null;

            for (Puid puid : track.getPuids()) {
                old = trackByPuid.get(puid.toString());
//                System.out.println("fetcher: old: " + old);
                if (null != old)
                    break;
            }

            if (null != old) {
                filePath = old.getFilepath();
                format = old.getFormat();
            }

            track.setFilepath(filePath);
            track.setFormat(format);
        }
        return newAlbum;
	}
	 
}
 
