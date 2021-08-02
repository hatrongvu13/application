package com.jax.authentication.api.authentications;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginRequestDTO {
    @NotNull
    String mbToken;
}
