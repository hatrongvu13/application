package com.jax.authen.api.controllers;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/2fa", produces = MediaType.APPLICATION_JSON_VALUE)
@Api("2FA authentication controller")
public class TwoFactorAuthenticationController {

}
