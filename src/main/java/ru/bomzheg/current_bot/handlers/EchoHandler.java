package ru.bomzheg.current_bot.handlers;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bomzheg.SnakeGram.Dispatcher;
import ru.bomzheg.SnakeGram.Handler;
import ru.bomzheg.SnakeGram.SendHelper;

import java.util.ArrayList;

public class EchoHandler implements Handler {
    private final SendHelper sender;
    private String prefix = "Получено:\n";

    public EchoHandler(SendHelper sender) {
        this.sender = sender;
    }

    public EchoHandler(SendHelper sender, String prefix) {
        this.sender = sender;
        this.prefix = prefix;
    }

    /** {@inheritDoc} */
    @Override
    public void handle(BotApiObject event) throws TelegramApiException {
        Message message = (Message) event;
        sender.sendMessage(message.getChatId(), prefix + message.getText());

    }

    /** {@inheritDoc} */
    @Override
    public void register(Dispatcher dispatcher) {
        dispatcher.registerMessageHandler(this, new ArrayList<>());
    }
}
