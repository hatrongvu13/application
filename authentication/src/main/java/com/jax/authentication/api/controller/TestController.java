package com.jax.authentication.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Test Controller")
public class TestController {

    @SneakyThrows
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiOperation(value = "All people !")
    public String all() {
        return "Public content.";
    }
}
