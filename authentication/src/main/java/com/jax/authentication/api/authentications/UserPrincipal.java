package com.jax.authentication.api.authentications;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserPrincipal implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Collection<? extends GrantedAuthority> authorities;
    private TokenUser tokenUser;
    private String id;

    public UserPrincipal() {
        super();
    }

    public UserPrincipal(TokenUser tokenUser, Collection<? extends GrantedAuthority> authorities) {
        this.id = tokenUser.getId().toString();
        this.tokenUser = tokenUser;
        this.authorities = authorities;
    }

    public static UserPrincipal create(TokenUser tokenUser) {
//        List<GrantedAuthority> authorities = tokenUser.getRoles().stream().map((scope) -> new SimpleGrantedAuthority(scope.getName().name())).collect(Collectors.toList());

//        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("USERS"));

        List<GrantedAuthority> authorities = tokenUser.getRoles().stream().map( r -> new SimpleGrantedAuthority(r.getName().name())).collect(Collectors.toList());

        return new UserPrincipal(
                tokenUser,
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return tokenUser.getPassword();
    }

    @Override
    public String getUsername() {
        return tokenUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
