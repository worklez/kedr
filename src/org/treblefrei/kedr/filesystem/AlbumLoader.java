package org.treblefrei.kedr.filesystem;

import org.treblefrei.kedr.model.Album;
import org.treblefrei.kedr.model.Track;

import java.io.File;

public class AlbumLoader {
 
	public static Album getAlbum(String directory) {
        File dir = new File(directory);
        String []children = dir.list();

        if (children == null)
            return null;

        String title = dir.getName();
        Album album = new Album(title);

        for (String filename : children) {
            String filepath = new File(directory, filename).getAbsolutePath();
            Track track = TrackLoader.getTrack(filepath);

            if (track != null)
                album.addTrack(track);
        }

		return album;
	}
	 
}
 
