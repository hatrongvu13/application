package com.jax.bot.api.controllers;

import com.jax.bot.api.services.TelegramService;
import com.jax.bot.data.request.MessageRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/telegram", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Telegram bot controller")
public class TelegramTestController {

    @Autowired
    TelegramService telegramService;

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendMessage(@RequestBody @Valid MessageRequest messageRequest) {
        return telegramService.sendMessage(messageRequest.getMessage());
    }
}
