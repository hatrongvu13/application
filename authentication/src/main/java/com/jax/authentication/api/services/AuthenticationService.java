package com.jax.authentication.api.services;

import com.jax.authentication.data.dto.request.LoginRequest;
import com.jax.authentication.data.dto.request.SignupRequest;
import com.jax.authentication.data.dto.response.TokenDTO;

public interface AuthenticationService {

    TokenDTO login(LoginRequest request);

    TokenDTO signup(SignupRequest request);
}
