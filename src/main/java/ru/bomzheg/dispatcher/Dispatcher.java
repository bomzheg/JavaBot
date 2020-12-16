package ru.bomzheg.dispatcher;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

public class Dispatcher {

    private static class RegisteredHandler {
        public Class<? extends BotApiObject> event;
        public Handler handler;
        public ArrayList<Filter> filters;

        public RegisteredHandler(Class<? extends BotApiObject> event, Handler handler, ArrayList<Filter> filters) {
            this.event = event;
            this.handler = handler;
            this.filters = filters;
        }
    }

    private ArrayList<RegisteredHandler> handlers = new ArrayList<>();

    public void registerMessageHandler(Handler handler, ArrayList<Filter> filters) {
        handlers.add(new RegisteredHandler(Message.class, handler, filters));
    }

    public Handler getHandlerForMessage(Message message) {
        for (RegisteredHandler registeredHandler: handlers) {
            if (checkFilters(registeredHandler.filters, message)) {
                return registeredHandler.handler;
            }
        }
        return null;
    }

    private static boolean checkFilters(ArrayList<Filter> filters, BotApiObject event) {
        for (Filter filter: filters) {
            if (!filter.check(event)) {
                return false;
            }
        }
        return true;
    }
}
