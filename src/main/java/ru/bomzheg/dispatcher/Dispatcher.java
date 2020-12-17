package ru.bomzheg.dispatcher;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface Dispatcher {
    boolean checkFilters(List<Filter> filters, BotApiObject event);

    void registerMessageHandler(Handler handler, List<Filter> filters);

    Handler getHandlerForMessage(Message message);
}
