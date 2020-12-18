package ru.bomzheg.current_bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.bomzheg.SnakeGram.*;
import ru.bomzheg.current_bot.config.BotConfig;
import ru.bomzheg.current_bot.handlers.EchoHandler;
import ru.bomzheg.current_bot.handlers.StartHandler;


public class Main {
    private static BotConfig botConfig;
    private static final Logger logger = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        botConfig = new BotConfig();

        CurrentBot currentBot = new CurrentBot();

        Dispatcher dispatcher = new DispatcherImp();
        currentBot.setDispatcher(dispatcher);

        SendHelper sendHelper = new SendHandlerImp(currentBot);

        Handler startHandler = new StartHandler(sendHelper);
        startHandler.register(dispatcher);

        Handler echoHandler = new EchoHandler(sendHelper, "Received:\n\n");
        echoHandler.register(dispatcher);

        configureBot(currentBot);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(currentBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void configureBot(CurrentBot currentBot) {
        logger.info("Configuring bot with token and log chat id ...");

        currentBot.setBotToken(botConfig.getBotToken());
        currentBot.setBotUsername(botConfig.getBotUsername());
    }
}
