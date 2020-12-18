package ru.bomzheg.SnakeGram;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatcherImp implements Dispatcher {

    private static class RegisteredHandler {
        public Handler handler;
        public List<Filter> filters;

        public RegisteredHandler(Handler handler, List<Filter> filters) {
            this.handler = handler;
            this.filters = filters;
        }
    }

    private final Map<Class<? extends BotApiObject>, List<RegisteredHandler>> handlers = new HashMap<>();

    @Override
    public void registerMessageHandler(Handler handler, List<Filter> filters) {
        List<RegisteredHandler> registeredHandlers = handlers.getOrDefault(Message.class, new ArrayList<>());
        registeredHandlers.add(new RegisteredHandler(handler, filters));
        handlers.put(Message.class, registeredHandlers);
    }

    @Override
    public Handler getHandlerForMessage(Message message) {
        if (handlers.containsKey(Message.class)) {
            for (RegisteredHandler registeredHandler : handlers.get(Message.class)) {
                if (checkFilters(registeredHandler.filters, message)) {
                    return registeredHandler.handler;
                }
            }
        }
        return null;
    }
    public boolean checkFilters(List<Filter> filters, BotApiObject event) {
        for (Filter filter: filters) {
            if (!filter.check(event)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void processUpdate(Update update) {
        Handler handler;
        if (update.getMessage() != null) {
            handler = getHandlerForMessage(update.getMessage());
            if (handler != null) {
                execute(handler, update.getMessage());
            }
        }

    }

    @Override
    public void execute(Handler handler, BotApiObject event) {
        try{
            handler.handle(event);
        } catch (TelegramApiException e) {
            CatchErrors(e, event);
        }
    }

    @Override
    public void CatchErrors(Exception exception, BotApiObject event) {
        try {
            throw exception;
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
