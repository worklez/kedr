package org.treblefrei.kedr.model;

import org.treblefrei.kedr.core.Updatable;

import java.util.List;

public class Workspace implements Updatable {
 
	private List<Album> albums;
	 
	private List<Updatable> updatableWidgets;
	 
	private Album album;
	 
	public boolean addAlbum(Album album) {
		return false;
	}
	 
	public boolean addUpdatableWidget(Updatable updatableWidget) {
		return false;
	}
	 
	public List<Album> getAlbums() {
		return null;
	}

    public boolean perfomed() {
        return false;
    }
}
 
