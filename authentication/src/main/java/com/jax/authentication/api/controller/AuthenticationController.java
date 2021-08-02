package com.jax.authentication.api.controller;

import com.jax.authentication.api.authentications.TokenDTO;
import com.jax.authentication.api.authentications.UserInfoDTO;
import com.jax.authentication.api.authentications.UserPrincipal;
import com.jax.authentication.api.services.AuthenticationService;
import com.jax.authentication.data.dto.request.LoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Authentication Controller")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @SneakyThrows
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Login")
    public TokenDTO login(@Valid @RequestBody LoginRequest request) {
        return authenticationService.login(request);
    }

    @SneakyThrows
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "Info")
    public UserInfoDTO info(@ApiIgnore @AuthenticationPrincipal UserPrincipal currentUser) {
        return authenticationService.info(currentUser);
    }

}
