package com.jax.authen.api.services;

import com.jax.authen.api.authentications.UserPrincipal;
import com.jax.authen.data.requests.LoginRequest;
import com.jax.authen.data.requests.RegisterRequest;
import com.jax.authen.data.requests.TokenRequest;
import com.jax.authen.data.responses.AuthorizationResponse;
import com.jax.authen.data.responses.InfoResponse;

public interface OAuthorService {
    AuthorizationResponse accountLogin(LoginRequest loginRequest);

    AuthorizationResponse tokenLogin(TokenRequest tokenRequest);

    InfoResponse info(UserPrincipal currentUser);

    AuthorizationResponse register(RegisterRequest registerRequest);
}
