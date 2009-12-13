package org.treblefrei.kedr.filesystem;

import org.treblefrei.kedr.model.Album;
import org.treblefrei.kedr.model.Track;

import java.io.File;

public class AlbumSaver {
 
	public static boolean saveAlbum(Album album) {
        for (Track track : album.getTracks()) {
            try {
                TrackSaver.saveTrack(track);
            } catch (TrackIOException e) {
                // problem with track
            }
        }
        
        return true;
	}


}
 
