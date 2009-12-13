package org.treblefrei.kedr.model;

import org.treblefrei.kedr.database.Puid;

import java.util.LinkedList;
import java.util.List;

public class Track {
 
	private String artist = "";
	private String genre = "";
	private String year = "";
	private String trackNumber = "";
	private String totalTracks = "";
	private long duration = 0;
	private String title = "";
	private String album = "";
	private String filepath = "";
    private String format = "";
    private List<Puid> puids;


    public Track() {
        puids = new LinkedList<Puid>();
    }
    
    public List<Puid> getPuids() {
        return puids;
    }

    public void addPuid(Puid puid) {
        puids.add(puid);
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public String getTotalTracks() {
        return totalTracks;
    }

    public void setTotalTracks(String totalTracks) {
        this.totalTracks = totalTracks;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
 
