package ru.bomzheg.pigra;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.bomzheg.pigra.config.BotConfig;

import java.util.logging.Logger;

public class Main {
    private static BotConfig botConfig;
    private static Logger logger;

    public static void main(String[] args) {
        configureLogger();
        botConfig = new BotConfig();
        PigraBot pigraBot = new PigraBot();
        configureBot(pigraBot);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(pigraBot);
            pigraBot.onStartUp();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void configureLogger() {
        logger = Logger.getLogger(Main.class.getName());
    }

    public static void configureBot(PigraBot pigraBot) {
        logger.fine("Configuring bot with token and log chat id ...");
        pigraBot.setBotToken(botConfig.getBotToken());
        pigraBot.setLogChatId(botConfig.getLogChatId());
        pigraBot.setBotUsername(botConfig.getBotUsername());
    }
}
