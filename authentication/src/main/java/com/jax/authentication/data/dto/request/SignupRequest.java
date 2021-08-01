package com.jax.authentication.data.dto.request;

import com.jax.authentication.data.enums.ERole;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequest {

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(min= 4, max = 40)
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String mobile;

    private Set<String> roles;
}
