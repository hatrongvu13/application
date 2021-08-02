package com.jax.authentication.api.services.impl;

import com.jax.authentication.api.authentications.*;
import com.jax.authentication.api.services.AuthenticationService;
import com.jax.authentication.data.dto.request.LoginRequest;
import com.jax.authentication.data.entities.Roles;
import com.jax.authentication.data.enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    TokenProvider tokenProvider;

    @Override
    public TokenDTO login(LoginRequest request) {
        Set<Roles> roles = new HashSet<>();
        Roles r = new Roles();
        r.setName(ERole.ROLE_ADMIN);
        roles.add(r);

        TokenUser tokenUser = new TokenUser();
        tokenUser.setCif("");
        tokenUser.setEmail("admin@gmail.com");
        tokenUser.setDob("Dob");
        tokenUser.setFullname("nguyen van a");
        tokenUser.setIdCardNo("123451234");
        tokenUser.setMobile("1235234234");
        tokenUser.setAuthorities(roles);

        String token = tokenProvider.issueToken(UserPrincipal.create(tokenUser));
        return new TokenDTO(token);

    }

    @Override
    public UserInfoDTO info(UserPrincipal currentUser) {
        return new UserInfoDTO(currentUser.getTokenUser());
    }
}
