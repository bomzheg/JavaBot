package ru.bomzheg.SnakeGram;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * helper class to easy send messages
 */
public interface SendHelper {
    /**
     * easy send message
     * @param chatId where to send
     * @param text what to send
     * @return message on success
     * @throws TelegramApiException on failure error from telegram
     */
    @SuppressWarnings("UnusedReturnValue")
    Message sendMessage(long chatId, String text) throws TelegramApiException;
}
