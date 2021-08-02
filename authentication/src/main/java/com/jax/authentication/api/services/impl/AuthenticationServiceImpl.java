package com.jax.authentication.api.services.impl;

import com.jax.authentication.api.authentications.*;
import com.jax.authentication.api.services.AuthenticationService;
import com.jax.authentication.data.dto.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    TokenProvider tokenProvider;

    @Override
    public TokenDTO login(LoginRequest request) {
        TokenUser tokenUser = new TokenUser();
        tokenUser.setCif("1235");
        tokenUser.setEmail("admin@gmail.com");
        tokenUser.setDob("Dob");
        tokenUser.setFullname("nguyen van a");
        tokenUser.setIdCardNo("123451234");
        tokenUser.setMobile("1235234234");

        String token = tokenProvider.issueToken(UserPrincipal.create(tokenUser));
        return new TokenDTO(token);

    }

    @Override
    public UserInfoDTO info(UserPrincipal currentUser) {
        return new UserInfoDTO(currentUser.getTokenUser());
    }
}
