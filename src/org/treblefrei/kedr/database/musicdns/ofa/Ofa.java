package org.treblefrei.kedr.database.musicdns.ofa;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import org.treblefrei.kedr.audio.AudioDecoderException;
import org.treblefrei.kedr.audio.DecodedAudioData;

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
    public static String createPrint(byte[] samples, int sRate, int stereo) {
        NativeLong nlSize = new NativeLong(samples.length);
        // LITTLE_ENDIAN = 0, BIG_ENDIAN = 1
        return COfa.instance.ofa_create_print(samples, 0, nlSize, sRate, stereo);
    }

    public static String createPrint(DecodedAudioData data) throws AudioDecoderException, FileNotFoundException {
            int channels = data.getChannels();
            if (channels > 2) {
                throw new RuntimeException("too many audio channels");
            }
            return Ofa.createPrint(data.getData(), data.getSampleRate(), channels - 1);
    }
}