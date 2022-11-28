package com.capstone.notechigima.config.auth;

import com.capstone.notechigima.domain.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AccountDetails implements UserDetails {

    private User user;

    public AccountDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public int getUserId() {
        return this.user.getUserId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList();
        authorities.add(() -> user.getRole().getKey());
        return authorities;
    }
}
