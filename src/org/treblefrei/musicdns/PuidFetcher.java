package org.treblefrei.musicdns;

import org.treblefrei.audio.AudioDecoder;
import org.treblefrei.audio.AudioDecoderException;
import org.treblefrei.audio.DecodedAudioData;
import org.treblefrei.musicdns.ofa.Ofa;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: worklez
 * Date: 07.12.2009
 * Time: 14:22:02
 */

public class PuidFetcher {
    private static String clientId = "a7f6063296c0f1c9b75c7f511861b89b";
    private static String clientVersion = "Example 0.9.3";
    private static String clientUserAgent = "libofa_example";
    private static String queryUrl = "http://ofa.musicdns.org/ofa/1/track";

    private static String queryFormat =
        "cid=%s&" +     // Client ID
        "cvr=%s&" +     // Client Version
        "fpt=%s&" +     // Fingerprint
        "rmd=%d&" +     // m = 1: return metadata; m = 0: only return id.
        "brt=%d&" +     // bitrate (kbps)
        "fmt=%s&" +     // File extension (e.g. mp3, ogg, flac)
        "dur=%d&" +    // Length of track (milliseconds)
        "art=%s&" +     // Artist name. If there is none, send "unknown"
        "ttl=%s&" +     // Track title. If there is none, send "unknown"
        "alb=%s&" +     // Album name. If there is none, send "unknown"
        "tnm=%d&" +     // Track number in album. If there is none, send "0"
        "gnr=%s&" +     // Genre. If there is none, send "unknown"
        "yrr=%s&" +     // Year. If there is none, send "0"
        "enc=%s&" +     // Encoding. e = true: ISO-8859-15; e = false: UTF-8 (default). Optional.
        "\r\n";

    // FIXME: ought to take an Album
    public static List<String> fetchPuids(String filename) throws AudioDecoderException, IOException, XPathExpressionException, ParserConfigurationException, SAXException {
            DecodedAudioData audioData = AudioDecoder.getSamples(filename, 135);
            String fingerPrint = Ofa.createPrint(audioData);

            URL url = new URL(queryUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", clientUserAgent);
            conn.setDoOutput(true);

            String query = String.format(queryFormat,
                    clientId, clientVersion, fingerPrint, 0, 0, audioData.getFormat(), audioData.getDuration(),
                    "unknown", "unknown", "unknown", 0, "unknown", 0, "");

            System.out.println("Query: " + query);

            conn.getOutputStream().write(query.getBytes());

            List<String> puids = parseXml(conn.getInputStream());
            conn.disconnect();
        return puids;
    }

    public static List<String> parseXml(InputStream is) throws ParserConfigurationException, XPathExpressionException, IOException, SAXException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        // domFactory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(is);

        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr
         = xpath.compile("//puid-list/puid");

        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

//        System.out.println(nodes.toString());
//        System.out.println(nodes.getLength());

        List<String> puids = new LinkedList<String>();
        for (int i = 0; i < nodes.getLength(); i++) {
            String id = nodes.item(i).getAttributes().getNamedItem("id").getNodeValue();
            puids.add(id);
            System.out.println(id);
        }
        return puids;
    }

}
