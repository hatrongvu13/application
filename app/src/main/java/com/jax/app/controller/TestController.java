package com.jax.app.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    @RequestMapping(value = "/demo/{guys}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String hello(@PathVariable("guys") String guys) {
        return guys;
    }
}
