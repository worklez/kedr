package org.treblefrei.ofa;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import org.treblefrei.audio.AudioDecoder;
import org.treblefrei.audio.AudioDecoderException;
import org.treblefrei.audio.DecodedAudioData;

import java.io.FileNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * User: worklez
 * Date: 05.12.2009
 * Time: 23:53:37
 */

interface COfa extends Library {
    COfa instance = (COfa) Native.loadLibrary("ofa", COfa.class);
    String ofa_create_print(byte[] samples, int byteOrder, NativeLong size, int sRate, int stereo);
}

public class Ofa {
    static String createPrint(byte[] samples, int sRate, int stereo) {
        NativeLong nlSize = new NativeLong(samples.length);
        // LITTLE_ENDIAN = 0, BIG_ENDIAN = 1
        return COfa.instance.ofa_create_print(samples, 0, nlSize, sRate, stereo);
    }

    static String createPrint(String filename) throws AudioDecoderException, FileNotFoundException {
            DecodedAudioData data = AudioDecoder.getSamples(filename, 135);
            int channels = data.getChannels();
            if (channels > 2) {
                throw new RuntimeException("too many audio channels");
            }
            return Ofa.createPrint(data.getData(), data.getSampleRate(), channels - 1);
    }
}