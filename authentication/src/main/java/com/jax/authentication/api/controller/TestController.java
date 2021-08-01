package com.jax.authentication.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Test controller")
@CrossOrigin
public class TestController {

    @SneakyThrows
    @ApiOperation(value = "public api")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all() {
        return "Public content";
    }

    @SneakyThrows
    @ApiOperation(value = "User api")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String user() {
        return "User content";
    }

    @SneakyThrows
    @ApiOperation(value = "Mod api")
    @RequestMapping(value = "/mod", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MODERATOR')")
    public String mod() {
        return "Mod content";
    }

    @SneakyThrows
    @ApiOperation(value = "Admin api")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Admin content";
    }
}
