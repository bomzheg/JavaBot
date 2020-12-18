package ru.bomzheg.current_bot.config;

public class BotConfig  extends Config{

    private String botToken;
    private long logChatId;
    private String botUsername;

    /** {@inheritDoc} */
    protected String getDefaultPropertiesFileName() {
        return "bot_config.properties";
    }

    public long getLogChatId() {
        return logChatId;
    }

    public String getBotToken() {
        return botToken;
    }

    /** {@inheritDoc} */
    protected void loadConfig() {
        loadConfigFromProperties();
        loadConfigFromEnv();
    }

    private void loadConfigFromProperties() {
        try {
            logChatId = Long.parseLong(properties.getProperty("bot.log_chat.id"));
        } catch (NumberFormatException e) {
            throw new RuntimeException("don't receive chat_id for log chat");
        }
    }

    private void loadConfigFromEnv() {
        botToken = System.getenv("BOT_TOKEN");
        botUsername = System.getenv("BOT_USERNAME");
    }

    public String getBotUsername() {
        return botUsername;
    }
}
