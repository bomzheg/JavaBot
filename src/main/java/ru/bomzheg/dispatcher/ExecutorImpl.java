package ru.bomzheg.dispatcher;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ExecutorImpl implements Executor{
    private Dispatcher dispatcher;

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void processUpdate(Update update) {
        Handler handler;
        if (update.getMessage() != null) {
            handler = dispatcher.getHandlerForMessage(update.getMessage());
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
