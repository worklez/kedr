package org.treblefrei.kedr.database;

import org.musicbrainz.JMBWSException;
import org.treblefrei.kedr.audio.AudioDecoderException;
import org.treblefrei.kedr.database.musicbrainz.MusicBrainz;
import org.treblefrei.kedr.database.musicdns.PuidFetcher;
import org.treblefrei.kedr.model.Album;
import org.treblefrei.kedr.model.Track;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AlbumInfoFetcher {
 
	public void fetchAlbumInfo(Album album) throws IOException, SAXException, AudioDecoderException, XPathExpressionException, ParserConfigurationException, JMBWSException {
        Set<Puid> allPuids = new HashSet<Puid>();
        Map<Track, Set<Puid>> puids = PuidFetcher.fetchPuids(album);
	    for (Set<Puid> puidSet : puids.values()) {
            allPuids.addAll(puidSet);
        }

        // ask musicbrainz for intersection of albums of tracks returned by puid
        // ask musicbrainz for album by returned id
        Set<Album> albums = MusicBrainz.getAlbumsByPuids(allPuids);  // TODO "offer album saving" (tm)
                                                                    // Or, maybe, we would just choose the first one?
                                                                    // guess many albums would be very rare
	}
	 
}
 
