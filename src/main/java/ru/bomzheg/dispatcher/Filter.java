package ru.bomzheg.dispatcher;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;

public interface Filter {
    boolean check (BotApiObject event);
}
