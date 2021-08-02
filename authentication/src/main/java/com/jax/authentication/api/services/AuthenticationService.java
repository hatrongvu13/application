package com.jax.authentication.api.services;

import com.jax.authentication.api.authentications.TokenDTO;
import com.jax.authentication.api.authentications.UserInfoDTO;
import com.jax.authentication.api.authentications.UserPrincipal;
import com.jax.authentication.data.dto.request.LoginRequest;

public interface AuthenticationService {

    TokenDTO login(LoginRequest request);

    UserInfoDTO info(UserPrincipal currentUser);
}
