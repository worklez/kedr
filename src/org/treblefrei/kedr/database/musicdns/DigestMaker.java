package org.treblefrei.kedr.database.musicdns;

import org.treblefrei.kedr.audio.AudioDecoder;
import org.treblefrei.kedr.audio.AudioDecoderException;
import org.treblefrei.kedr.audio.DecodedAudioData;
import org.treblefrei.kedr.database.musicdns.ofa.Ofa;
import org.treblefrei.kedr.model.Album;
import org.treblefrei.kedr.model.Track;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DigestMaker {

	public static Map<Track, Digest> getAlbumDigest(Album album) throws AudioDecoderException, FileNotFoundException {
        List<Track> tracks = album.getTracks();
        Map<Track, Digest> digests = new HashMap<Track, Digest>();
        for (Track track : tracks) {
            DecodedAudioData audioData = AudioDecoder.getSamples(track.getFilepath(), 135);
            track.setDuration(audioData.getDuration());
            track.setFormat(audioData.getFormat());
            digests.put(track, new Digest(Ofa.createPrint(audioData)));
        }
		return digests;
	}
	 
}
 
