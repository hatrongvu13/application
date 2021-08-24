package com.jax.authen.api.authentications;

import lombok.Data;

@Data
public class TokenUser {
    private String username;
    private String fullName;
    private String email;
    private String mobile;
}
