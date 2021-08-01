package com.jax.authentication.data.mapper;

import com.jax.authentication.api.authentications.TokenUser;
import com.jax.authentication.data.entities.User;
import org.springframework.stereotype.Component;

@Component
public class TokenUserMapper {
    
    public TokenUser convert(User user) {
        TokenUser tokenUser = new TokenUser();
        tokenUser.setId(user.getId().toString());
        tokenUser.setUsername(user.getUsername());
        tokenUser.setPassword(user.getPassword());
        tokenUser.setEmail(user.getEmail());
        tokenUser.setRoles(user.getRoles());
        return tokenUser;
    }
}
