package ru.bomzheg.dispatcher;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface SendHelper {
    Message sendMessage(long chatId, String text) throws TelegramApiException;
}
