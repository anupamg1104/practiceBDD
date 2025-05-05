package com.framework.utils;

import java.io.*;
import java.nio.file.*;

public class FileUtility {

    public static void writeFile(String fileName, String content) throws IOException {
        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void deleteFile(String fileName) throws IOException {
        Files.delete(Paths.get(fileName));
    }
}
