package org.treblefrei.musicdns;

import org.treblefrei.audio.AudioDecoder;
import org.treblefrei.audio.AudioDecoderException;
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
 * Date: 05.12.2009
 * Time: 23:18:58
 */

public class TestMain {
    public static void main(String args[]) throws IOException, SAXException, XPathExpressionException, ParserConfigurationException {
        try {
            PuidFetcher.fetchPuids("2.mp3");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AudioDecoderException ex) {
            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
