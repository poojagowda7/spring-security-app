package com.security.auth.client;


import com.security.common.service.UserDetailsFetcher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", path = "/security")
public interface FeignUserDetailsFetcher extends UserDetailsFetcher {

    @GetMapping("/security/fetchUserByUsername")
    UserDetails loadUserByUsername(@RequestParam String username);
}


