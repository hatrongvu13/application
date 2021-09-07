package com.jax.authen.data.responses;

import lombok.Data;

@Data
public class TokenResponse {

    private String token;

    private String ID;

    private String secret;

}
