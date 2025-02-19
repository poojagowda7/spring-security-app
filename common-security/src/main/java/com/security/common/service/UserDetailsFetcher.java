package com.security.common.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsFetcher {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
