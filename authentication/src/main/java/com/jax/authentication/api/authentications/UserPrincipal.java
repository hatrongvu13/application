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
    private String id;

    private Collection<? extends GrantedAuthority> authorities;
    private TokenUser tokenUser;

    public UserPrincipal(TokenUser tokenUser, Collection<? extends GrantedAuthority> authorities) {
        super();

        this.tokenUser = tokenUser;
        this.authorities = authorities;
    }

    public UserPrincipal() {
        super();
    }


    public static UserPrincipal create(TokenUser tokenUser) {
        List<GrantedAuthority> authorities = tokenUser
                .getAuthorities()
                .stream()
                .map((scope) -> new SimpleGrantedAuthority(scope.getName().name())).collect(Collectors.toList());

//        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("USERS"));
//        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return tokenUser.getCif();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
