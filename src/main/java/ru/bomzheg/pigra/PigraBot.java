package ru.bomzheg.pigra;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class PigraBot extends TelegramLongPollingBot {

    private String botToken;
    private String botUsername;
    private long logChatId;
    private static Logger logger;

    public PigraBot() {
        logger = Logger.getLogger(PigraBot.class.getName());
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
        if (!(update.hasMessage() && update.getMessage().hasText())) {
            return;
        }
        sendMessage(update.getMessage().getChatId(), "Получено " + update.getMessage().getText());
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
    public void onStartUp() {
        sendMessage(logChatId, "Bot started");
    }

    @SuppressWarnings("UnusedReturnValue")
    private Message sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        Message sentMessage;
        try {
            sentMessage = execute(message);
        } catch (TelegramApiException e) {
            logger.log(Level.WARNING, "cant send message", e);
            sendStackTrace(e);
            sentMessage = null;
        }
        return sentMessage;
    }
}
