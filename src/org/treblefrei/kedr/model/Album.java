package org.treblefrei.kedr.model;

import org.treblefrei.kedr.core.Updatable;

import java.util.LinkedList;
import java.util.List;

public class Album {
 
	private List<Track> tracks;

    private List<Updatable> updatables;

    private String title;

    // There could not be album without any title!
    public Album(String title) {
        this.title = title;
        tracks = new LinkedList<Track>();
        updatables = new LinkedList<Updatable>();        
    }

	public boolean sync(Album target) {
        title = target.getTitle();
        tracks = target.getTracks();
        //updatables = target.getUpdatables();
        notifyUpdatables();
		return true;
	}

    public List<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        tracks.add(track);
        notifyUpdatables();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyUpdatables();
    }

    private void notifyUpdatables() {
        for (Updatable updatable : updatables)
            updatable.perfomed();
    }

    public void addUpdatable(Updatable uptable) {
        updatables.add(uptable);
    }

    public void removeUpdatable(Updatable updatable) {
        updatables.remove(updatable);
    }

    private List<Updatable> getUpdatables() {
        return updatables;
    }

    

    @Override
    public String toString() {
        return title;
    }
}
 
