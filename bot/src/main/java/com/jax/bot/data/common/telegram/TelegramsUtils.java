package com.jax.bot.data.common.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InputMediaPhoto;
import com.pengrad.telegrambot.request.SendMediaGroup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.MessagesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TelegramsUtils {
    private TelegramBot bot;

    @Autowired
    public TelegramsUtils(@Value("${telegramBot.token}") String token) {
        this.bot = new TelegramBot(token);
    }

    public String sendMessage(Long chatId, String message) {
        SendResponse response = bot.execute(new SendMessage(chatId, message));
        return response.message().text();
    }

    public String sendMessageAttachment(Long chatId, InputMediaPhoto[] images, String message) {
        if (images != null) {
            SendMediaGroup sendMediaGroup = new SendMediaGroup(chatId, images);
            MessagesResponse response = bot.execute(sendMediaGroup);
            return response.messages().toString();
        } else {
            return this.sendMessage(chatId,message);
        }
    }
}
