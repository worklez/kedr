package org.treblefrei.kedr.model;

import org.treblefrei.kedr.core.Updatable;

import java.util.LinkedList;
import java.util.List;

public class Album {
 
	private List<Track> tracks;
	 
	private Updatable parent;

    private String title;

    public Album() {
        tracks = new LinkedList<Track>();
    }

	public boolean sync(Album target) {
		return false;
	}

    public List<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
 
