package ru.bomzheg.dispatcher;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Executor {
    void processUpdate(Update update);
    void execute(Handler handler, BotApiObject event);
    void CatchErrors(Exception exception, BotApiObject event);
    void setDispatcher(Dispatcher dispatcher);
}
