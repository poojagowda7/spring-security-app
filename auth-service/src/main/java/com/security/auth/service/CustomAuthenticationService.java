package com.security.auth.service;

import com.security.common.service.UserDetailsFetcher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationService {
    private final UserDetailsFetcher userDetailsFetcher;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationService( UserDetailsFetcher userDetailsFetcher,
                                        PasswordEncoder passwordEncoder,
                                        AuthenticationManager authenticationManager){
        this.userDetailsFetcher = userDetailsFetcher;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public Authentication authenticate(String username, String password){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);  // Authenticate using AuthenticationManager
    }
}
