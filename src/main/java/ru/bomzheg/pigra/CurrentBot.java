package ru.bomzheg.pigra;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bomzheg.dispatcher.Executor;

import java.util.logging.Level;
import java.util.logging.Logger;


public class CurrentBot extends TelegramLongPollingBot {

    private String botToken;
    private String botUsername;
    private long logChatId;
    private static final Logger logger = Logger.getLogger(CurrentBot.class.getName());
    private Executor executor;

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public void setLogChatId(long logChatId) {
        this.logChatId = logChatId;
    }

    public void setBotToken(String token) {
        botToken = token;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        executor.processUpdate(update);
    }
}
