package com.epam.talker.controller;

import com.epam.talker.model.User;
import com.epam.talker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/{newName}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @PathVariable String newName) {
        User userToUpdate = userService.getUserById(id);
        userService.updateUser(userToUpdate, newName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User userToDelete = userService.getUserById(id);
        userService.deleteUser(userToDelete);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{followerId}/follow/{followedId}")
    public ResponseEntity followUser(
            @PathVariable Long followerId,
            @PathVariable Long followedId) {
        userService.followUser(followerId, followedId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{followerId}/unfollow/{followedId}")
    public ResponseEntity unFollowUser(
            @PathVariable Long followerId,
            @PathVariable Long followedId) {
        userService.unFollowUser(followerId, followedId);
        return ResponseEntity.noContent().build();
    }

}
