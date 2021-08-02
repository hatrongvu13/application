package com.jax.authentication.api.authentications;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenUser {
    private String cif;
    private String fullname;
    private String dob;
    private String idCardNo;
    private String mobile;
    private String email;
}
