package ru.bomzheg.current_bot.filters;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bomzheg.dispatcher.Filter;

public class CommandStartFilter  implements Filter {

    @Override
    public boolean check(BotApiObject event) {
        Message message = (Message) event;
        return message.getText().equals("/start");
    }
}
