package com.greenspring.green.config.auth;

import com.greenspring.green.model.User;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

//Spring Security gets login requests and proceed login, when complete return UserDetails type objects
//and save unique session storage
@Getter
public class PrincipalDetails implements UserDetails {

    private User user; // Composistion

    public PrincipalDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(()->{return "ROLE_"+user.getRole();});

        return collectors;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // true : not expired
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // true : not locked
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // ture : not expired
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // true : enabled
    @Override
    public boolean isEnabled() {
        return true;
    }
}
