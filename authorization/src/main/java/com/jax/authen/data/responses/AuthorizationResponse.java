package com.jax.authen.data.responses;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthorizationResponse {
    @NotNull
    private String token;
}
