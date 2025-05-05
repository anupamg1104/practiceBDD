package com.framework.utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class XMLUtils {

    public static String getTagValue(String filePath, String tagName) {
        String value = null;
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(tagName);
            if (nodeList.getLength() > 0) {
                value = nodeList.item(0).getTextContent();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
    
    
    /**
     * Fetches XML content from the provided URL and returns it as a string.
     *
     * @param xmlUrl The URL pointing to the XML content.
     * @return XML content as a string.
     */
    public static String getXmlContentAsString(String xmlUrl) {
        StringBuilder xmlContent = new StringBuilder();

        try {
            URL url = new URL(xmlUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed to fetch XML. HTTP status: " + status);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                xmlContent.append(inputLine).append("\n");
            }

            in.close();
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching XML content from URL: " + xmlUrl);
        }

        return xmlContent.toString();
    }
}
