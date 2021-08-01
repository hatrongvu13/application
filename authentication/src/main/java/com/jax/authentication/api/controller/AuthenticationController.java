package com.jax.authentication.api.controller;

import com.jax.authentication.api.services.AuthenticationService;
import com.jax.authentication.data.dto.request.LoginRequest;
import com.jax.authentication.data.dto.request.SignupRequest;
import com.jax.authentication.data.dto.response.TokenDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Authentication Controller", value = "Test authentication")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @SneakyThrows
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "Login")
    public TokenDTO login(@Valid @RequestBody LoginRequest request) {
        return authenticationService.login(request);
    }

    @SneakyThrows
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ApiOperation(value = "Sign up")
    public TokenDTO signup(@Valid @RequestBody SignupRequest request) {
        return authenticationService.signup(request);
    }
}
