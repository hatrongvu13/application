package com.jax.authen.api.controllers;

import com.jax.authen.api.authentications.UserPrincipal;
import com.jax.authen.api.services.OAuthorService;
import com.jax.authen.data.requests.LoginRequest;
import com.jax.authen.data.requests.RegisterRequest;
import com.jax.authen.data.requests.TokenRequest;
import com.jax.authen.data.responses.AuthorizationResponse;
import com.jax.authen.data.responses.InfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "OAuthentication controller")
public class OAuthorController {

    @Autowired
    OAuthorService authorService;

    @SneakyThrows
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Account login")
    public AuthorizationResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authorService.accountLogin(loginRequest);
    }

    @SneakyThrows
    @RequestMapping(value = "/login", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Token login")
    public AuthorizationResponse login(@RequestBody @Valid TokenRequest tokenRequest) {
        return authorService.tokenLogin(tokenRequest);
    }

    @SneakyThrows
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "Info account")
    public InfoResponse info(@AuthenticationPrincipal @ApiIgnore UserPrincipal currentUser) {
        return authorService.info(currentUser);
    }

    @SneakyThrows
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register account")
    public AuthorizationResponse register(@RequestBody @Valid RegisterRequest registerRequest) {
        return authorService.register(registerRequest);
    }

}
