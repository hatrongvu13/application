package com.jax.authentication.api.authentications;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenUser {

    private String username;
    private String password;
    private String mobile;
    private String email;

}
