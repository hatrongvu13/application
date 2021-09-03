package com.jax.authen.api.controllers;

import com.jax.authen.data.responses.AuthorizationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "OAuthentication controller")
public class OAuthorController {

    @SneakyThrows
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "login")
    public AuthorizationResponse login() {
        return null;
    }

}
