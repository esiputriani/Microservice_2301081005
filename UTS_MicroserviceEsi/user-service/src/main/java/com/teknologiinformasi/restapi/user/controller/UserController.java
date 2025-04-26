package com.teknologiinformasi.restapi.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teknologiinformasi.restapi.user.model.User;
import com.teknologiinformasi.restapi.user.service.UserService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // GET semua user
    @GetMapping
    public List<User>getAllUsers() {
        log.info("GET /api/users accessed");
        return userService.getAll();
    }

    // GET user berdasarkan id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.info("GET /api/users/{} accessed", id);
        return userService.getById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST membuat user baru
    @PostMapping
    public User createUser(@RequestBody User user) {
        log.info("POST /api/users accessed");
        return userService.createUser(user);
    }

    // PUT update user yang ada
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        log.info("PUT /api/users/{} accessed", id);
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            log.error("User with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("DELETE /api/users/{} accessed", id);
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (RuntimeException e) {
            log.error("Failed to delete user with id {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}