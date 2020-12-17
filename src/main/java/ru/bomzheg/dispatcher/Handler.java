package ru.bomzheg.dispatcher;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Handler {
    void handle(BotApiObject event) throws TelegramApiException;
    void register(Dispatcher dispatcher);
}
