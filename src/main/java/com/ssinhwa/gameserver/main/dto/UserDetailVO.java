package com.ssinhwa.gameserver.main.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
public class UserDetailVO implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String playerName;
    private String email;
    private Set<GrantedAuthority> authorities;
    private boolean emailVerified;
    private boolean locked;

    public UserDetailVO(String username, String password, String email, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.emailVerified = false;
        this.authorities = Collections.unmodifiableSet(au
    }

    public void changeEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public void changeLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return (!locked && emailVerified);
    }
}

