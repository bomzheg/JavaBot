package ru.bomzheg.dispatcher;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

/**
 * That class save handlers and on update can find handler
 */
public interface Dispatcher {

    /**
     * Check filters on that event and return true if all of filters pass
     * @param filters array of filters
     * @param event event that can pass or not all filters
     * @return true if all filters pass. if any of filter false - result will be false
     */
    boolean checkFilters(List<Filter> filters, BotApiObject event);

    /**
     * Take handler and they filters and save it for conditional use when it need to route Update
     * @param handler for registration
     * @param filters filters that need to pass for handler
     */
    void registerMessageHandler(Handler handler, List<Filter> filters);

    /**
     * routing Update for handlers
     * @param message for that searching handler
     * @return a handler if a suitable one exists
     */
    Handler getHandlerForMessage(Message message);

    /**
     * get update and run handler if that exist
     * @param update update received by bot and need to be processed
     */
    void processUpdate(Update update);

    /**
     *
     * @param handler need to be executed
     * @param event event on that will be executed handler
     */
    void execute(Handler handler, BotApiObject event);

    /**
     * in that method catching errors and it may be handled
     * @param exception current exception
     * @param event event in process that throws exception
     */
    void CatchErrors(Exception exception, BotApiObject event);
}
