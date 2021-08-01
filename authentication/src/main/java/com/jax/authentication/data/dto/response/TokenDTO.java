package com.jax.authentication.data.dto.response;

import lombok.Data;

@Data
public class TokenDTO {
    String token;

    public TokenDTO(String token) {
        this.token = token;
    }

}
