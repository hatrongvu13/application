package com.jax.exception.api.controller;

import com.jax.exception.data.constant.exception.BadRequestException;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping()
public class DemoController {

    @SneakyThrows
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void hello() {
        throw new BadRequestException("{file.type.error}");
    }
}
