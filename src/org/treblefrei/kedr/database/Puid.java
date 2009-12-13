package org.treblefrei.kedr.database;

/**
 * Created by IntelliJ IDEA.
 * User: worklez
 * Date: 08.12.2009
 * Time: 23:22:57
 */

public class Puid {
    private String puid;

    public Puid(String puid) {
        this.puid = puid;
    }

    public void setPuid(String puid) {
        this.puid = puid;
    }

    public String toString() {
        return puid;
    }
}
