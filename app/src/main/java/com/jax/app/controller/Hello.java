package com.jax.app.controller;

import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
public class Hello {

    @SneakyThrows
    @RequestMapping(value = "/{guys}", method = RequestMethod.GET)
    public String hello(@PathVariable("guys") String guys) {
        return "Hello : " + guys;
    }
}
