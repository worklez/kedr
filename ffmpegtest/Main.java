package ffmpegtest;

import com.xuggle.ferry.IBuffer;
import com.xuggle.xuggler.IAudioSamples;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IPacket;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;

public class Main {

    private static IStreamCoder getContainerAudioStreamCoder(IContainer container) {
        int numStreams = container.getNumStreams();
        IStreamCoder audioStreamCoder = null;

        for (int i = 0; i < numStreams; i++) {
            IStream stream = container.getStream(i);
            IStreamCoder streamCoder = stream.getStreamCoder();

            // The first audio stream codec
            if (streamCoder.getCodecType() == ICodec.Type.CODEC_TYPE_AUDIO) {
                audioStreamCoder = streamCoder;
                break;
            }
        }

        if (audioStreamCoder == null)
            throw new RuntimeException("No audio stream");

        return audioStreamCoder;
    }

    public static void main(String[] args) {
        IContainer container = IContainer.make();

        if (container.open("1.flac", IContainer.Type.READ, null) < 0) {
            throw new RuntimeException("failed to open");
        }


        IPacket packet = IPacket.make();
        IStreamCoder streamCoder = getContainerAudioStreamCoder(container);

        int channels = streamCoder.getChannels();
        int sampleRate = streamCoder.getSampleRate();
        int samplesToRead = 135 * channels * sampleRate;
        IBuffer buffer = IBuffer.make(null, IBuffer.Type.IBUFFER_SINT16, 
                samplesToRead, true);
        IAudioSamples audioSamples = IAudioSamples.make(buffer, channels,
                IAudioSamples.Format.FMT_NONE);

        System.out.println("sampleRate="+sampleRate+" channels="+
                channels+" samplesToRead="+samplesToRead);

        int position = 0;
        while (container.readNextPacket(packet) >= 0 && position < samplesToRead * 2) {
            int ret = streamCoder.decodeAudio(audioSamples, packet, position);
            System.out.println("Got it");
            if (ret >= 0)
                position += ret;
            else {
                System.err.println("streamCoder.decodeAudio error, break");
                break;
                }
            }

        System.out.println("Success!");

        container.close();
    }
}
