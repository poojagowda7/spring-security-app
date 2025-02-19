package com.security.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username cannot be empty")
    @Length(min = 3, message = "Username must contain at least 3 characters")
    private String username;

    @Column(nullable = false)
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @Column(nullable = false)
    private String roles;

    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Email cannot be empty")
    private String email;
}
