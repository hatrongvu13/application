package com.jax.authentication.api.authentications;

import lombok.Data;

@Data
public class TokenDTO {
    String token;

    public TokenDTO(String token) {
        this.token = token;
    }
}
