package ru.bomzheg.pigra.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuring abstract class. Constructor can take file name and load config from that file
 * child classes must implemented methods:
 * getDefaultPropertiesFileName - return string with name of configure file by default
 * loadConfig - load all variables from file or env to fields
 */
public abstract class Config {
    protected Properties properties = new Properties();

    /**
     * @return String with properties file name
     */
    protected abstract String getDefaultPropertiesFileName();

    Config() {
        loadConfigFromFile(getDefaultPropertiesFileName());
        loadConfig();
    }

    Config(String fileName) {
        loadConfigFromFile(fileName);
        loadConfig();
    }

    /**
     * that method must load values from file or env to fields
     */
    protected abstract void loadConfig();

    private void loadConfigFromFile(String fileName) {
        try{
            loadProperties(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected void loadProperties(String fileName) throws IOException {
        try (InputStream propertiesIS = createConfigurationStream(fileName)) {
            if (propertiesIS == null) {
                throw new IOException(String.format("Configuration file %s not found", fileName));
            }
            properties.load(propertiesIS);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private InputStream createConfigurationStream(String fileName) {
        return Config.class.getResourceAsStream(normalizeFileName(fileName));
    }

    private static String normalizeFileName(String fileName) {
        return (fileName.startsWith("/") ? "" : "/") + fileName;
    }
}
