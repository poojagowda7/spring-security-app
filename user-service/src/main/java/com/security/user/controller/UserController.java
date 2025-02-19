package com.security.user.controller;

import com.security.user.entity.User;
import com.security.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/security")
@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/registerUser")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
        //return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user){
        User updateUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updateUser);
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/fetchUserByUsername")
    public UserDetails loadUserByUsername(@RequestParam String username) {
        return userService.loadUserByUsername(username);
    }




}
