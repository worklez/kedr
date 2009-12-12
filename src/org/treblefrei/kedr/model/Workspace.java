package org.treblefrei.kedr.model;

import org.treblefrei.kedr.core.Updatable;

import java.util.LinkedList;
import java.util.List;

public class Workspace implements Updatable {
 
	private List<Album> albums;
	 
	private List<Updatable> updatableWidgets;
	 
	private Album album;

    public Workspace() {
        albums = new LinkedList<Album>();
        updatableWidgets = new LinkedList<Updatable>();
    }

	public boolean addAlbum(Album album) {
		albums.add(album);
        album.addUpdatable(this);
        System.err.println("Workspace.addAlbum()");
        notifyUpdatableWidgets();
        return true;
	}
	 
	public boolean addUpdatableWidget(Updatable updatableWidget) {
        updatableWidgets.add(updatableWidget);
		return true;
	}

    private void notifyUpdatableWidgets() {
        for (Updatable updatableWidget : updatableWidgets)
            updatableWidget.perfomed();
    }


	public List<Album> getAlbums() {
		return albums;
	}

    public boolean perfomed() {
        notifyUpdatableWidgets();
        return true;
    }
}
 
