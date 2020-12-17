package ru.bomzheg.dispatcher;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * It must process event.
 */
public interface Handler {
    /**
     * receive event and make all things that necessary
     * @param event event for processing
     * @throws TelegramApiException if telegram return error
     */
    void handle(BotApiObject event) throws TelegramApiException;
    void register(Dispatcher dispatcher);
}
