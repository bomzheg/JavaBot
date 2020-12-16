package ru.bomzheg.dispatcher;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;

public interface Handler {
    void handle(BotApiObject event);
}
