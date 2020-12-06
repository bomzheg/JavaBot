package ru.bomzheg.pigra;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {

    private String botToken;
    private long logChatId;

    public long getLogChatId() {
        return logChatId;
    }

    public String getBotToken() {
        return botToken;
    }

    private Path loadConfigPath() {
        final String DEFAULT_CONFIG_PATH = "conf/config.ini";
        String envConfigPath = System.getenv("CONFIG_PATH");
        if (envConfigPath != null && !envConfigPath.isBlank()) {
            return Paths.get(envConfigPath);
        }
        final ClassLoader loader = this.getClass().getClassLoader();
        URL configUrl = loader.getResource(DEFAULT_CONFIG_PATH);
        if (configUrl != null) {
            String configPathString = configUrl.toString();
            configPathString = configPathString.replace("file:/", "");
            return Paths.get(configPathString);
        }
        throw new RuntimeException("Don't have configuration file");
    }

    public void loadConfig() throws IOException {
        Path configFile = loadConfigPath();
        Properties props = new Properties();
        props.load(new FileInputStream(configFile.toFile()));
        try {
            logChatId = Long.parseLong(props.getProperty("LOG_CHAT_ID"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        botToken = System.getenv("BOT_TOKEN");
    }
}
