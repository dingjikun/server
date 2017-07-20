package com.ding.buyaround.security;

import com.ding.buyaround.model.UserEntity;
import com.ding.buyaround.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by pc on 2017/6/1.
 */
@Component
public class AppUserDetailService implements UserDetailsService {

    private final UserService userService;

    public AppUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public final UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        final UserEntity user = this.userService.lookup(username);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
