package ru.bomzheg.SnakeGram;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SendHandlerImp implements SendHelper {
    private final DefaultAbsSender bot;

    public SendHandlerImp(DefaultAbsSender bot) {
        this.bot = bot;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Message sendMessage(long chatId, String text) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        return bot.execute(message);
    }
}
