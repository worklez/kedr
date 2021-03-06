package org.treblefrei.kedr.audio;

import com.xuggle.xuggler.*;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ripper
 */
public class AudioDecoder {

    private static final int READ_BUFFER_SIZE = 1024;

    private static IStream getContainerAudioStream(IContainer container) {
        int numStreams = container.getNumStreams();
        IStream audioStream = null;

        for (int i = 0; i < numStreams; i++) {
            IStream stream = container.getStream(i);
            IStreamCoder streamCoder = stream.getStreamCoder();

            // The first audio stream codec
            if (streamCoder.getCodecType() == ICodec.Type.CODEC_TYPE_AUDIO) {
                audioStream = stream;
                break;
            }
        }

        if (audioStream == null) {
            throw new RuntimeException("No audio stream");
        }

        return audioStream;
    }

    public static DecodedAudioData getSamples(String filename, int seconds) throws FileNotFoundException, AudioDecoderException {
        IContainer container = IContainer.make();

        if (container.open(filename, IContainer.Type.READ, null) < 0) {
            System.err.println("File not found: "+filename);
            throw new FileNotFoundException();
        }

        IPacket packet = IPacket.make();
        IStream audioStream = getContainerAudioStream(container);
        IStreamCoder audioCoder = audioStream.getStreamCoder();

        int audioStreamId = audioStream.getId();
        int channels = audioCoder.getChannels();
        int sampleRate = audioCoder.getSampleRate();
        int samplesToRead = seconds * channels * sampleRate;
        long duration = (long)(1000 * audioStream.getDuration() * audioStream.getTimeBase().getDouble());

        String format = audioCoder.getCodec().getName();

        byte[] decodedData = new byte[samplesToRead * 2];

        if (audioCoder.open() < 0) {
            throw new AudioDecoderException();
        }

        int position = 0;

        //System.err.println("samplesToRead="+samplesToRead);

        boolean finished = false;

        try {
            while (container.readNextPacket(packet) >= 0 && !finished) {

                if (packet.getStreamIndex() == audioStreamId) {
                    int offset = 0;
                    IAudioSamples samples = IAudioSamples.make(READ_BUFFER_SIZE,
                            audioCoder.getChannels());


                    while (offset < packet.getSize()) {
                        int bytesDecoded = audioCoder.decodeAudio(samples, packet, offset);

                        if (bytesDecoded < 0) {
                            throw new AudioDecoderException();
                        }

                        offset += bytesDecoded;

                        if (samples.isComplete()) {
                            byte[] rawBytes = samples.getData()
                                    .getByteArray(0, samples.getSize());

                            if (position + rawBytes.length < decodedData.length) {
                                System.arraycopy(rawBytes, 0, decodedData, position,
                                        rawBytes.length);
                            } else {
                                System.arraycopy(rawBytes, 0, decodedData, position,
                                        decodedData.length - position);
                                finished = true;
                                break;
                            }

                            position += rawBytes.length;
                        }
                    }
                }
            }
        }

        catch (AudioDecoderException e) {
            // File is lesser then 'seconds' seconds
        }

        container.close();

        return new DecodedAudioData(channels, sampleRate, decodedData, duration, format);
    }

    public static void main(String[] args) {
        try {
            AudioDecoder.getSamples("1.flac", 135);
            AudioDecoder.getSamples("2.mp3", 135);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AudioDecoderException ex) {
            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}