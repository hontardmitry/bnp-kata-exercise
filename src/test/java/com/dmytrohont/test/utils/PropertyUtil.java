package com.dmytrohont.test.utils;

import javax.naming.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyUtil {

    private PropertyUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ENVIRONMENT = System.getProperty("env", "prod");

    public static String getProperty(String key) {
        try {
            return loadProperties().getProperty(key);
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key, String defaultValue) {
        try {
            return loadProperties().getProperty(key, defaultValue);
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties loadProperties() throws ConfigurationException {
        Properties props = new Properties();
        String path = String.format("com/dmytrohont/test/config/%s.properties", ENVIRONMENT);
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            if (input == null) throw new ConfigurationException("Missing com.dmytrohont.test.config: " + path);
            props.load(input);
        } catch (IOException | ConfigurationException e) {
            throw new ConfigurationException("Failed to load com.dmytrohont.test.config: " + path);
        }
        return props;
    }
}
