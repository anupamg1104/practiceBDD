package com.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;
    private static final Properties properties = new Properties();

    public static Properties initProperties() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream(".//src/main/resources/config/application.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

//    public static String getProperty(String key) {
//        if (prop == null) {
//            initProperties();
//        }
//        return prop.getProperty(key);
//    }
    
 // Load properties from config file
    public static void loadConfig() {
        try (FileInputStream fis = new FileInputStream(".//src/main/resources/config/application.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + e.getMessage(), e);
        }
    }

    // Get property value by key
    public static String getProperty(String key) {
    	 loadConfig() ;
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("Property '" + key + "' not found or is empty in configuration file");
        }
        return value;
    }
}



//package com.framework.utils;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//public class ConfigReader {
//
// private static Properties properties;
//
// public static void loadConfig() {
//     properties = new Properties();
//     try {
//         FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
//         properties.load(fis);
//     } catch (IOException e) {
//         e.printStackTrace();
//     }
// }
//
// public static String getProperty(String key) {
//     if (properties == null) {
//         loadConfig();
//     }
//     return properties.getProperty(key);
// }
//}
//
