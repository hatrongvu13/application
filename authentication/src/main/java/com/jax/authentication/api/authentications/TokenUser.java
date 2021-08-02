package com.jax.authentication.api.authentications;

import com.jax.authentication.data.entities.Roles;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TokenUser {
    private String cif;
    private String fullname;
    private String dob;
    private String idCardNo;
    private String mobile;
    private String email;
    private Set<Roles> authorities;
}
