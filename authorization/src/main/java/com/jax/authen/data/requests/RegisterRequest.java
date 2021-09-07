package com.jax.authen.data.requests;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class RegisterRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String fullName;

    @NotBlank
    @Size(min = 6)
    private String password;

    @Email
    private String email;

    @NotBlank
    private String mobile;

    @NotBlank
    LocalDate dob;
}
