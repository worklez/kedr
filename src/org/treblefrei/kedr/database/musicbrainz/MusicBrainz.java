package org.treblefrei.kedr.database.musicbrainz;

import org.musicbrainz.JMBWSException;
import org.musicbrainz.Query;
import org.musicbrainz.model.Release;
import org.musicbrainz.model.Track;
import org.musicbrainz.webservice.filter.TrackFilter;
import org.musicbrainz.webservice.includes.ReleaseIncludes;
import org.musicbrainz.webservice.includes.TrackIncludes;
import org.musicbrainz.wsxml.element.TrackResult;
import org.musicbrainz.wsxml.element.TrackSearchResults;
import org.treblefrei.kedr.database.Puid;
import org.treblefrei.kedr.model.Album;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MusicBrainz {
 
    public static Set<Album> getAlbumsByPuids(Set<Puid> puids) throws JMBWSException {
        Set<String> allReleasesIds = null; //new HashSet<String>();
        for (Puid puid : puids) {
            Track t = getTrackByPuid(puid);
            List<Release> releases = t.getReleases();
            Set<String> trackReleaseIds = new HashSet<String>();
            for (Release release : releases) {
                trackReleaseIds.add(release.getId());
//                System.out.println(release);
            }
            if (null != allReleasesIds && trackReleaseIds.size() != 0) {
                allReleasesIds.retainAll(trackReleaseIds);
            } else if (0 != trackReleaseIds.size()) {
                allReleasesIds = trackReleaseIds;
            }
        }

//        System.out.println("mbrnz: !" + allReleasesIds);

        if (null == allReleasesIds || 0 == allReleasesIds.size()) {
            System.err.println("strange, no releases found");
            return new HashSet<Album>();
        }

        Set<Album> albums = new HashSet<Album>();

        for (String id : allReleasesIds) {
            Release release = getReleaseById(id);
            Album album = new Album(release.getTitle());
//            System.err.println("Album found: " + release.getTitle() + " (" + release.getId() + " == " + id + ")");
            List<Track> tracks = getReleaseTracks(release.getId());//release.getTrackList().getTracks();
            int trackNumber = 1;
            for (Track track : tracks) {
                org.treblefrei.kedr.model.Track albumTrack = new org.treblefrei.kedr.model.Track();
//                System.err.println("\t Track found: " + track.getTitle());
                albumTrack.setAlbum(release.getTitle());
                albumTrack.setArtist(track.getArtist().getName()); // TODO: offer artist name aliases
                albumTrack.setDuration(track.getDuration());
                albumTrack.setTitle(track.getTitle());
                albumTrack.setTotalTracks(String.valueOf(tracks.size())); // changed to string due to change of Track
                albumTrack.setTrackNumber(String.valueOf(trackNumber));
                List<String> puidStrings = getTrackById(track.getId()).getPuids();
                for (String puidString : puidStrings) {
                    albumTrack.addPuid(new Puid(puidString));
                }
                albumTrack.setYear(release.getEarliestReleaseDate());
                album.addTrack(albumTrack);
                trackNumber++; // TODO: is it correct? are they in order?
            }
            albums.add(album);
        }
        return albums;
    }

    public static List<Track> getReleaseTracks(String id) throws JMBWSException {
        Query query = new Query();
        TrackFilter filter = new TrackFilter();
//        System.err.println(id.substring(id.lastIndexOf("/") + 1));
        filter.setReleaseId(id.substring(id.lastIndexOf("/") + 1)); // ugly but it can't get it working another way
        TrackSearchResults results = query.getTracks(filter);

        List<Track> tracks = new LinkedList<Track>();
        for (TrackResult trackResult : results.getTrackResults()) {
            tracks.add(trackResult.getTrack());
//            System.err.println("getter: " + trackResult.getTrack().getTitle());
        }
        return tracks;
    }

//    private static class PuidQuery extends Query {
//        TrackSearchResults getTracksWithPuids(TrackFilter filter) throws JMBWSException {
//            TrackIncludes includes = new TrackIncludes();
//            includes.setArtist(true);
//            includes.setPuids(true);
//            includes.setReleases(true);
//            return getFromWebService("track", "", includes, filter).getTrackResults();
//        }
//    }

    public static Track getTrackByPuid(Puid puid) throws JMBWSException {
        Query query = new Query();
        TrackFilter filter = new TrackFilter();
        filter.setPuid(puid.toString());
        TrackSearchResults results = query.getTracks(filter);
        Track t = results.getTrackResults().get(0).getTrack(); // FIXME getting the first one
//        System.err.println(t.getArtist().getName() + " - " + t.getTitle() + " from " + t.getReleases().get(0).getTitle());
        return t;
    }

    public static Release getReleaseById(String id) throws JMBWSException {
        Query query = new Query();
        ReleaseIncludes includes = new ReleaseIncludes();
        includes.setArtist(true);
        includes.setTracks(true);
        includes.setCounts(true);
        includes.setReleaseEvents(true);
        return query.getReleaseById(id, includes);
    }

    public static Track getTrackById(String id) throws JMBWSException {
        Query query = new Query();
        TrackIncludes includes = new TrackIncludes();
        includes.setArtist(true);
        includes.setPuids(true);
        includes.setReleases(true);
        return query.getTrackById(id, includes);
    }

}
 
