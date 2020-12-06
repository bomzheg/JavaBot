package ru.bomzheg.pigra;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {
    private static Config config;
    private static Logger logger;

    public static void main(String[] args) {
        configureLogger();
        config = new Config();
        try {
            config.loadConfig();
        } catch (IOException e) {
            logger.warning("Can't load config. Make sure that you are pass config path in Environment Variable");
        }
        PigraBot pigraBot = new PigraBot();
        configureBot(pigraBot);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(pigraBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void configureLogger() {
        logger = Logger.getLogger(Main.class.getName());
    }

    public static void configureBot(PigraBot pigraBot) {
        logger.fine("Configuring bot with token and log chat id ...");
        pigraBot.setToken(config.getBotToken());
        pigraBot.setLogChatId(config.getLogChatId());
    }
}
