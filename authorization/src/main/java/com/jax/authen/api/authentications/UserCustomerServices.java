package com.jax.authen.api.authentications;

import com.jax.authen.data.entities.Users;
import com.jax.authen.data.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserCustomerServices implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.usersRepository.findUsersByUsername(username).orElse(null);
        if (!Objects.isNull(user)) {

            return null;
        } else {
            throw new UsernameNotFoundException(String.format("Username %s not found !", username));
        }
    }
}
