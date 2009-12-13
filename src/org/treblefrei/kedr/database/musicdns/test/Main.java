package org.treblefrei.kedr.database.musicdns.test;

import org.musicbrainz.JMBWSException;
import org.treblefrei.kedr.database.Puid;
import org.treblefrei.kedr.database.musicbrainz.MusicBrainz;
import org.treblefrei.kedr.model.Album;
import org.treblefrei.kedr.model.Track;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: worklez
 * Date: 08.12.2009
 * Time: 23:57:44
 */

public class Main {
        public static void main(String args[]) throws IOException, SAXException, XPathExpressionException, ParserConfigurationException, JMBWSException {
//        try {
            Album album = new Album("Foo");

            Track track1 = new Track();
            Track track2 = new Track();

            track1.setFilepath("1.wav");
            track2.setFilepath("2.mp3");

            album.addTrack(track1);
            album.addTrack(track2);

            //Map<Track, Set<Puid>> puids = PuidFetcher.fetchPuids(album);
            //MusicBrainz.getPuidInfo(puids.get(track1).get(0));
//            MusicBrainz.getTrackByPuid(new Puid("3a33de2e-66d4-f718-7b59-2e1b9b02042d"));
//            MusicBrainz.getTrackByPuid(new Puid("049e33d2-8d4e-7bb1-ce7e-fdea205b1be9"));
            Set<Puid> puids = new HashSet<Puid>();
            puids.add(new Puid("3a33de2e-66d4-f718-7b59-2e1b9b02042d"));
            MusicBrainz.getAlbumsByPuids(puids);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (AudioDecoderException ex) {
//            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
