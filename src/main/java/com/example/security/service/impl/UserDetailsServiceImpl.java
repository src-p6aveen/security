package com.example.security.service.impl;

import com.example.security.entity.UserLoginProfile;
import com.example.security.repository.UserLoginProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserLoginProfileRepository repository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UserLoginProfile userLoginProfile = this.repository.findByUsername(username);
        if(userLoginProfile == null) {
            throw new UsernameNotFoundException("Unknown user "+ username);
        }
        return User.withUsername(userLoginProfile.getUsername())
                .password(userLoginProfile.getPassword())
                .authorities("ROLE_USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
