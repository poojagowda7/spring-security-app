package com.security.auth.service;

import com.security.auth.client.FeignUserDetailsFetcher;
import com.security.common.service.UserDetailsFetcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FeignUserDetailsService implements UserDetailsFetcher {

    private final FeignUserDetailsFetcher feignUserDetailsFetcher;

    public FeignUserDetailsService(FeignUserDetailsFetcher feignUserDetailsFetcher) {
        this.feignUserDetailsFetcher = feignUserDetailsFetcher;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return feignUserDetailsFetcher.loadUserByUsername(username);
    }
}

