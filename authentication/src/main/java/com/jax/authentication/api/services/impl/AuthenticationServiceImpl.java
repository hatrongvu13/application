package com.jax.authentication.api.services.impl;

import com.jax.authentication.api.authentications.TokenProvider;
import com.jax.authentication.api.authentications.TokenUser;
import com.jax.authentication.api.authentications.UserPrincipal;
import com.jax.authentication.api.services.AuthenticationService;
import com.jax.authentication.data.dto.request.LoginRequest;
import com.jax.authentication.data.dto.request.SignupRequest;
import com.jax.authentication.data.dto.response.TokenDTO;
import com.jax.authentication.data.entities.Roles;
import com.jax.authentication.data.entities.User;
import com.jax.authentication.data.enums.ERole;
import com.jax.authentication.data.mapper.TokenUserMapper;
import com.jax.authentication.data.repository.RoleRepository;
import com.jax.authentication.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenUserMapper tokenUserMapper;

//    AuthenticationServiceImpl() {}

    @Override
    public TokenDTO login(LoginRequest request) {

        User user = userRepository.findUserByUsername(request.getUsername()).orElse(null);

        if (Objects.isNull(user)) {
            logger.warn("Invalid user or password with username: {}", request.getUsername());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Account !");
        }


        String token = tokenProvider.issueToken(UserPrincipal.create(tokenUserMapper.convert(user)));

        return new TokenDTO(token);
    }

    @Override
    public TokenDTO signup(SignupRequest request) {

        if (userRepository.existsUserByUsername(request.getUsername())) {
            logger.warn("Username : {} is exist !", request.getUsername());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is used !");
        }
        if (userRepository.existsUserByEmail(request.getEmail())) {
            logger.warn("Email : {} is exist !", request.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is used !");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(encoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());
        newUser.setMobile(request.getMobile());
        newUser.setCreatedDate(LocalDateTime.now());
        newUser.setUpdatedDate(LocalDateTime.now());

        Set<String> strRoles = request.getRoles();
        Set<Roles> roles = new HashSet<>();
        if (strRoles == null) {
            Roles userRole = roleRepository.findRolesByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Roles adminRole = roleRepository.findRolesByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Roles modRole = roleRepository.findRolesByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Roles userRole = roleRepository.findRolesByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        newUser.setRoles(roles);
        newUser = userRepository.save(newUser);
        String token = tokenProvider.issueToken(UserPrincipal.create(tokenUserMapper.convert(newUser)));
        logger.info("Sign up successfully !");
        return new TokenDTO(token);
    }


}
