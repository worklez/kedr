package org.treblefrei.ofa;

import org.treblefrei.audio.AudioDecoder;
import org.treblefrei.audio.AudioDecoderException;
import org.treblefrei.audio.DecodedAudioData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: worklez
 * Date: 05.12.2009
 * Time: 23:18:58
 */

public class TestMain {
    public static void main(String args[]) throws IOException {
        try {
            DecodedAudioData audioData = AudioDecoder.getSamples("2.mp3", 135);
            String fingerPrint = Ofa.createPrint(audioData);
            String clientId = "a7f6063296c0f1c9b75c7f511861b89b";
            String clientVer = "Example 0.9.3";
            URL url = new URL("http://ofa.musicdns.org/ofa/1/track");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "libofa_example");
            conn.setDoOutput(true);

            String query = String.format("cid=%s&cvr=%s&fpt=%s&rmd=1&brt=0&art=unknown&ttl=unknown&alb=unknown&tnm=0&gnr=unknown&yrr=0&enc=&dur=%s&fmt=%s", clientId, clientVer, fingerPrint, audioData.getDuration(), audioData.getFormat());
            System.out.println("Query: " + query);
            //OutputStreamWriter wr = new OutputStreamWriter(
            conn.getOutputStream().write(query.getBytes());
            //wr.write();
            //wr.flush();
            //wr.close();

            Scanner s = new Scanner(conn.getInputStream());
            while (s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
            s.close();
            conn.disconnect();
            //System.out.println();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AudioDecoderException ex) {
            Logger.getLogger(AudioDecoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
