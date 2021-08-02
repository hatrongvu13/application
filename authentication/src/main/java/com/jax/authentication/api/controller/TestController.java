package com.jax.authentication.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

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

    @SneakyThrows
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ApiOperation(value = "User !")
//    @RolesAllowed("ROLE_VIEWER")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String user() {
        return "Private Content.";
    }

    @SneakyThrows
    @RequestMapping(value = "/mod", method = RequestMethod.GET)
    @ApiOperation(value = "Mod Content.")
    @PreAuthorize("hasRole('MODERATOR')")
    public String mod() {
        return "Mod Content.";
    }

    @SneakyThrows
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @ApiOperation(value = "Admin Content.")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Admin Content.";
    }
}
