package com.jax.authentication.api.authentications;

import com.jax.authentication.data.entities.Roles;
import com.jax.authentication.data.entities.User;
import com.jax.authentication.data.enums.ERole;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TokenUser{

    private String id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Set<Roles> roles;

}
