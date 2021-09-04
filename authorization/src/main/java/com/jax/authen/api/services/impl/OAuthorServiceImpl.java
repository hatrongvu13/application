package com.jax.authen.api.services.impl;

import com.jax.authen.api.authentications.UserPrincipal;
import com.jax.authen.api.services.OAuthorService;
import com.jax.authen.data.requests.LoginRequest;
import com.jax.authen.data.requests.RegisterRequest;
import com.jax.authen.data.requests.TokenRequest;
import com.jax.authen.data.responses.AuthorizationResponse;
import com.jax.authen.data.responses.InfoResponse;
import org.springframework.stereotype.Service;

@Service
public class OAuthorServiceImpl implements OAuthorService {
    @Override
    public AuthorizationResponse accountLogin(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public AuthorizationResponse tokenLogin(TokenRequest tokenRequest) {
        return null;
    }

    @Override
    public InfoResponse info(UserPrincipal currentUser) {
        return null;
    }

    @Override
    public AuthorizationResponse register(RegisterRequest registerRequest) {
        return null;
    }
}
