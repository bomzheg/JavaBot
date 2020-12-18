package ru.bomzheg.current_bot.handlers;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bomzheg.SnakeGram.Dispatcher;
import ru.bomzheg.SnakeGram.Filter;
import ru.bomzheg.SnakeGram.Handler;
import ru.bomzheg.SnakeGram.SendHelper;
import ru.bomzheg.current_bot.filters.CommandStartFilter;

import java.util.ArrayList;
import java.util.List;

public class StartHandler implements Handler {
    private final SendHelper sender;

    public StartHandler(SendHelper sender) {
        this.sender = sender;
    }

    @Override
    public void handle(BotApiObject event) throws TelegramApiException {
        Message message = (Message) event;
        sender.sendMessage(message.getChatId(), "Привет! я эхобот");
    }

    @Override
    public void register(Dispatcher dispatcher) {
        List<Filter> filters = new ArrayList<>();
        filters.add(new CommandStartFilter());
        dispatcher.registerMessageHandler(this, filters);
    }
}
