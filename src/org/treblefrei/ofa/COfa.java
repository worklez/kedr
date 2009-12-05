package org.treblefrei.ofa;

import com.sun.jna.Library;
import com.sun.jna.NativeLong;


/**
 * Created by IntelliJ IDEA.
 * User: worklez
 * Date: 05.12.2009
 * Time: 23:04:05
 */

public interface COfa extends Library {
    String ofa_create_print(byte[] samples, int byteOrder, NativeLong size, int sRate, int stereo);
}
