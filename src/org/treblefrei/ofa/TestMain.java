package org.treblefrei.ofa;

import org.treblefrei.audio.AudioDecoderException;
import org.treblefrei.audio.AudioDecoder;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: worklez
 * Date: 05.12.2009
 * Time: 23:18:58
 */

public class TestMain {
    public static void main(String args[]) {
        try {
            System.out.println(Ofa.createPrint("1.flac"));
            System.out.println(Ofa.createPrint("2.mp3"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AudioDecoderException ex) {
            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
