package com.jax.authentication.security;

import lombok.Data;

@Data
public class TokenDTO {
    String token;

    public TokenDTO(String token) {
        this.token = token;
    }
}
