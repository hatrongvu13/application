package com.jax.authen.data.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TokenRequest {

    @NotBlank
    private String token;

    private String ID;

//    @Size(min = 6, max = 8)
    private String secret;
}
