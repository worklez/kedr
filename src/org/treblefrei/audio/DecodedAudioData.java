package org.treblefrei.audio;

/**
 * Created by IntelliJ IDEA.
 * User: gleb
 * Date: 06.12.2009
 * Time: 2:02:04
 */
public class DecodedAudioData {

    public DecodedAudioData(int channels, int sampleRate, byte[] data, long duration, String format) {
        this.channels = channels;
        this.sampleRate = sampleRate;
        this.data = data;
        this.duration = duration;
        this.format = format;
    }


    public int getChannels() {
        return channels;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public byte[] getData() {
        return data;
    }

    public long getDuration() {
        return duration;
    }

    public String getFormat() {
        return format;
    }

    private int channels;
    private int sampleRate;
    private byte []data;
    private long duration;
    private String format;
}
