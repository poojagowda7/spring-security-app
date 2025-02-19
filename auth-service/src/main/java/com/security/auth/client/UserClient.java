package com.security.auth.client;

import com.security.auth.dto.UserResponse;
import com.security.common.service.UserDetailsFetcher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service-client", url = "${USER_SERVICE_URL:http://localhost:8080}", path = "/security")
public interface UserClient {

    @GetMapping("/getUserByUsername/{username}")
    UserResponse getUserByUsername(@PathVariable("username") String username);
}
