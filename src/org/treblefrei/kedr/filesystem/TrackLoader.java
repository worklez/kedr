package org.treblefrei.kedr.filesystem;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.treblefrei.kedr.model.Track;

import java.io.File;

public class TrackLoader {

    private static String getTagStringValue(Tag tag, FieldKey key, String defaultValue) {
        try {
            return tag.getFirst(key);
        }
        catch (KeyNotFoundException e) {}
        catch (UnsupportedOperationException e) {}
        catch (NullPointerException e) {}
        
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


	public static Track getTrack(String filename) throws TrackIOException {


        try {
            Track track = new Track();            
            AudioFile f = AudioFileIO.read(new File(filename));

            Tag tag = f.getTag();
            AudioHeader audioHeader = f.getAudioHeader();

            int duration = audioHeader.getTrackLength() * 1000; // we need in msec!
            String artist = getTagStringValue(tag, FieldKey.ARTIST, "Unknown artist");
            String album = getTagStringValue(tag, FieldKey.ALBUM, "Unknown album");
            String format = audioHeader.getFormat();
            String genre = getTagStringValue(tag, FieldKey.GENRE, "");
            String title = getTagStringValue(tag, FieldKey.TITLE, "Unknown track");
            String totalTracks = getTagStringValue(tag, FieldKey.TRACK_TOTAL, "");
            String trackNumber = getTagStringValue(tag, FieldKey.TRACK, "");
            String year = getTagStringValue(tag, FieldKey.YEAR, "");


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
        } catch (Exception e) {
            return null; // just skip
        }

//        } catch (CannotReadException e) {
//            // e.printStackTrace();
//            // throw new TrackIOException();
//            // just skip
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new TrackIOException();
//        } catch (TagException e) {
//            e.printStackTrace();
//            throw new TrackIOException();
//        } catch (ReadOnlyFileException e) {
//            e.printStackTrace();
//            throw new TrackIOException();
//        } catch (InvalidAudioFrameException e) {
//            e.printStackTrace();
//            throw new TrackIOException();
//        }
	}
	 
}
 
