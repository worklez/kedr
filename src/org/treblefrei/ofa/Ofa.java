package org.treblefrei.ofa;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;

/**
 * Created by IntelliJ IDEA.
 * User: worklez
 * Date: 05.12.2009
 * Time: 23:53:37
 */

public class Ofa {
    static String createPrint(byte[] samples, int sRate, int stereo) {
        COfa cofa = (COfa) Native.loadLibrary("ofa", COfa.class);
        NativeLong nlSize = new NativeLong(samples.length);
        // LITTLE_ENDIAN = 0, BIG_ENDIAN = 1
        return cofa.ofa_create_print(samples, 0, nlSize, sRate, stereo);
    }
}