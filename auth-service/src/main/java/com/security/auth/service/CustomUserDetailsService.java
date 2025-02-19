package com.security.auth.service;


import com.security.auth.client.UserClient;
import com.security.auth.dto.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserClient userClient;

    public CustomUserDetailsService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponse user = userClient.getUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword()) // Ensure password is stored encoded
                .roles(user.getRole().split(",")) // Convert roles to Spring Security format
                .build();
    }
}
