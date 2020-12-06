package ru.bomzheg.pigra;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class PigraBot extends TelegramLongPollingBot {

    private String botToken;
    private long logChatId;
    private static Logger logger;

    public PigraBot() {
        logger = Logger.getLogger(PigraBot.class.getName());
    }

    public void setLogChatId(long logChatId) {
        this.logChatId = logChatId;
    }

    public void setToken(String token) {
        botToken = token;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return "betaCurChangeBot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!(update.hasMessage() && update.getMessage().hasText())) {
            return;
        }
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("Получено " + update.getMessage().getText());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.log(Level.WARNING, "cant send message", e);
            sendStackTrace(e);

        }
    }

    private void sendStackTrace(Exception e) {
        sendLogMessage(e.getMessage());
    }

    private void sendLogMessage(String message_text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(logChatId));
        message.setText(message_text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.log(Level.SEVERE, "cant send log message", e);
            e.printStackTrace();
        }
    }
}
