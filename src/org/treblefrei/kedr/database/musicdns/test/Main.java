package org.treblefrei.kedr.database.musicdns.test;

import org.musicbrainz.JMBWSException;
import org.treblefrei.kedr.audio.AudioDecoderException;
import org.treblefrei.kedr.database.AlbumInfoFetcher;
import org.treblefrei.kedr.model.Album;
import org.treblefrei.kedr.model.Track;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: worklez
 * Date: 08.12.2009
 * Time: 23:57:44
 */

public class Main {
        public static void main(String args[]) throws IOException, SAXException, XPathExpressionException, ParserConfigurationException, JMBWSException, AudioDecoderException {
//        try {
            Album album = new Album("Foo");

            Track track1 = new Track();
            Track track2 = new Track();

            track1.setFilepath("1.mp3");
            track2.setFilepath("2.mp3");

            album.addTrack(track1);
            album.addTrack(track2);

            //Map<Track, Set<Puid>> puids = PuidFetcher.fetchPuids(album);
            //MusicBrainz.getPuidInfo(puids.get(track1).get(0));
//            MusicBrainz.getTrackByPuid(new Puid("3a33de2e-66d4-f718-7b59-2e1b9b02042d"));
//            MusicBrainz.getTrackByPuid(new Puid("049e33d2-8d4e-7bb1-ce7e-fdea205b1be9"));
//            Set<Puid> puids = new HashSet<Puid>();
//            puids.add(new Puid("3a33de2e-66d4-f718-7b59-2e1b9b02042d"));
//            MusicBrainz.getAlbumsByPuids(puids);
            Album album2 = AlbumInfoFetcher.fetchAlbumInfo(album);
            System.out.println("-----");
            System.out.println(album2.getTitle());
            for (Track track : album2.getTracks()) {
                System.out.println(String.format("%s %s - %s [%s]", track.getTrackNumber(), track.getArtist(), track.getTitle(), track.getFilepath()));
            }
            System.out.println("-----");
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (AudioDecoderException ex) {
//            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
