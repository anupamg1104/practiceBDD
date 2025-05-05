package com.framework.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVUtils {
	
	private static final String CSV_INPUT_PATH = "src/test/resources/testdata/urls.csv";
    private static final String CSV_OUTPUT_PATH = "target/failed_urls.csv";
    private static List<String> csvUrls = new ArrayList<>();
    private static List<String> failedUrls = new ArrayList<>();

    public static void writeFailedUrlsToCSV(List<String> failedUrls, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Failed URLs");
            writer.append("\n");
            for (String url : failedUrls) {
                writer.append(url);
                writer.append("\n");
            }
            System.out.println("Failed URLs exported to CSV successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void loadUrls() {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_INPUT_PATH))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length > 0) {
                    csvUrls.add(nextLine[0]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validateUrlsFromCsv() {
        for (String url : csvUrls) {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int code = connection.getResponseCode();
                if (code != 200) {
                    failedUrls.add(url + " => Status Code: " + code);
                }
            } catch (Exception e) {
                failedUrls.add(url + " => Exception: " + e.getMessage());
            }
        }
    }

    public static void logFailures() {
        writeToCsv(failedUrls);
    }

    public static void writeToCsv(List<String> data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_OUTPUT_PATH))) {
            for (String line : data) {
                writer.writeNext(new String[]{line});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<String> readURLs(String filePath) {
        List<String> urls = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                urls.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }

    public static void writeFailedUrls(List<String> failedUrls, String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (String url : failedUrls) {
                writer.write(url);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<String> readURLs1(String filePath) {
        List<String> urls = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    urls.add(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }

}
