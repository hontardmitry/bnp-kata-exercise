package utils;

import javax.naming.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyUtil {

    private PropertyUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ENVIRONMENT = System.getProperty("env", "prod");
    private static final Properties properties;

    static {
        try {
            properties = loadProperties();
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    private static Properties loadProperties() throws ConfigurationException {
        Properties props = new Properties();
        String path = String.format("config/%s.properties", ENVIRONMENT);
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            if (input == null) throw new ConfigurationException("Missing config: " + path);
            props.load(input);
        } catch (IOException | ConfigurationException e) {
            throw new ConfigurationException("Failed to load config: " + path);
        }
        return props;
    }
}
