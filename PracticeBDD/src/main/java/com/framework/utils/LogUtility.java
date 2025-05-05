package com.framework.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogUtility {
    private static Logger logger = Logger.getLogger(LogUtility.class);

    static {
        PropertyConfigurator.configure("log4j.properties");  // Path to log4j properties file
    }

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logError(String message) {
        logger.error(message);
    }

    public static void logWarn(String message) {
        logger.warn(message);
    }

    public static void logDebug(String message) {
        logger.debug(message);
    }
}

