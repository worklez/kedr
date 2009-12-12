package org.treblefrei.kedr.database.musicdns.test;

import org.treblefrei.kedr.audio.AudioDecoder;
import org.treblefrei.kedr.audio.AudioDecoderException;
import org.treblefrei.kedr.database.musicdns.PuidFetcher;
import org.treblefrei.kedr.model.Album;
import org.treblefrei.kedr.model.Track;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: worklez
 * Date: 08.12.2009
 * Time: 23:57:44
 */

public class Main {
        public static void main(String args[]) throws IOException, SAXException, XPathExpressionException, ParserConfigurationException {
        try {
            Album album = new Album("Foo");

            Track track1 = new Track();
            Track track2 = new Track();

            track1.setFilepath("1.wav");
            track2.setFilepath("2.mp3");

            album.addTrack(track1);
            album.addTrack(track2);

            PuidFetcher.fetchPuids(album);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AudioDecoderException ex) {
            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
