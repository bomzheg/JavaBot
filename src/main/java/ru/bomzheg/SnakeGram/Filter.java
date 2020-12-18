package ru.bomzheg.SnakeGram;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;

/**
 * Filter is a class that receive in check param event and checks if this event is suitable
 */
public interface Filter {
    /**
     * receive event and make some checks
     * @param event event need to be checked
     * @return whether the event passed through the filter successfully
     */
    boolean check (BotApiObject event);
}
