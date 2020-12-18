package ru.bomzheg.current_bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bomzheg.SnakeGram.Dispatcher;


public class CurrentBot extends TelegramLongPollingBot {

    private String botToken;
    private String botUsername;
    private Dispatcher dispatcher;

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
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
        dispatcher.processUpdate(update);
    }
}
