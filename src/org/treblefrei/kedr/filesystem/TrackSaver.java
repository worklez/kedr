package org.treblefrei.kedr.filesystem;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.*;
import org.jaudiotagger.tag.id3.ID3v24Tag;
import org.treblefrei.kedr.model.Track;

import java.io.File;
import java.io.IOException;

public class TrackSaver {

    private static void setTag(Tag tag, FieldKey key, String value) {
        try {
            /*
            try {
                tag.deleteField(key);
            }
            catch (KeyNotFoundException e) {}
              */
            tag.setField(key, value);
            //tag.addField(key, value);

        } catch (FieldDataInvalidException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {}
        // is thrown when trying to set non-number to tracknumber, wtf??
    }

    public static boolean saveTrack(Track track) throws TrackIOException {
        AudioFile f = null;

        try {
            f = AudioFileIO.read(new File(track.getFilepath()));
        } catch (CannotReadException e) {
            e.printStackTrace();
            throw new TrackIOException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new TrackIOException();
        } catch (TagException e) {
            e.printStackTrace();
            throw new TrackIOException();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
            throw new TrackIOException();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
            throw new TrackIOException();
        }

        Tag tag = f.getTag();
        //Tag tag = new ID3v24Tag();


        try {
            tag.setEncoding("UTF-8");
        } catch (FieldDataInvalidException e) {
            e.printStackTrace();
        } 
        System.err.println("Saving "+track.getTitle());
        
        setTag(tag, FieldKey.ARTIST, track.getArtist());
        setTag(tag, FieldKey.ALBUM, track.getAlbum());
        setTag(tag, FieldKey.GENRE, track.getGenre());
        setTag(tag, FieldKey.TITLE, track.getTitle());
        setTag(tag, FieldKey.TRACK_TOTAL, track.getTotalTracks());
        setTag(tag, FieldKey.TRACK, track.getTrackNumber());
        setTag(tag, FieldKey.YEAR, track.getYear());
        
        //f.setTag(tag);
        
        try {
            //AudioFileIO.write(f);
            f.commit();
        } catch (CannotWriteException e) {
            e.printStackTrace();
            throw new TrackIOException();
        }

        return true;
    }

}
 
