package org.treblefrei.kedr.filesystem;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.treblefrei.kedr.model.Track;

import java.io.File;
import java.io.IOException;

public class TrackLoader {

    private static String getTagStringValue(Tag tag, FieldKey key, String defaultValue) {
        try {
            return tag.getFirst(key);
        }
        catch (KeyNotFoundException e) {}
        catch (UnsupportedOperationException e) {}
        
        return defaultValue;
    }

    private static int getTagStringIntValue(Tag tag, FieldKey key, int defaultValue) {
        try {
            return Integer.valueOf(tag.getFirst(key));
        }
        catch (KeyNotFoundException e) {}
        catch (NumberFormatException e) {}
        catch (UnsupportedOperationException e) {}
        
        return defaultValue;
    }


	public static Track getTrack(String filename) {


        try {
            Track track = new Track();            
            AudioFile f = AudioFileIO.read(new File(filename));

            Tag tag = f.getTag();
            AudioHeader audioHeader = f.getAudioHeader();

            int duration = audioHeader.getTrackLength();
            String artist = getTagStringValue(tag, FieldKey.ARTIST, "Unknown artist");
            String album = getTagStringValue(tag, FieldKey.ALBUM, "Unknown album");
            String format = audioHeader.getFormat();
            String genre = getTagStringValue(tag, FieldKey.GENRE, "");;
            String title = getTagStringValue(tag, FieldKey.TITLE, "Unknown track");
            int totalTracks = getTagStringIntValue(tag, FieldKey.TRACK_TOTAL, 0);
            int trackNumber = getTagStringIntValue(tag, FieldKey.TRACK, 0);
            int year = getTagStringIntValue(tag, FieldKey.YEAR, 0);


            track.setDuration(duration);
            track.setArtist(artist);
            track.setAlbum(album);
            track.setFilepath(filename);
            track.setFormat(format);
            track.setGenre(genre);
            track.setTitle(title);
            track.setTotalTracks(totalTracks);
            track.setTrackNumber(trackNumber);
            track.setYear(year);

            return track;

        } catch (CannotReadException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TagException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

		return null;
	}
	 
}
 
