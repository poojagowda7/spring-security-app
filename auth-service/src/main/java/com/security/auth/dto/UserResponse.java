package com.security.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String username;
    private String password;
    private String role;
}
