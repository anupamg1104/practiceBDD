package com.framework.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONUtils {

    public static JsonNode readJsonFromFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = null;

        try {
            jsonData = mapper.readTree(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonData;
    }

    public static String getJsonValue(JsonNode jsonData, String key) {
        return jsonData.path(key).asText();
    }
}
