package com.jax.bot.api.services.impl;

import com.jax.bot.api.services.TelegramService;
import com.jax.bot.data.common.telegram.TelegramsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelegramServiceImpl implements TelegramService {

    @Autowired
    TelegramsUtils telegramsUtils;

    @Override
    public String sendMessage(String message) {
        return telegramsUtils.sendMessage(-490615408L,message);
    }
}
