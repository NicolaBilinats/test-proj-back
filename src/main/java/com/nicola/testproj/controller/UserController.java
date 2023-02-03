package com.nicola.testproj.controller;

import com.nicola.testproj.model.User;
import com.nicola.testproj.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Add a new User", response = User.class)
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.registerUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Login and get userId", response = User.class)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (!userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("No such user", HttpStatus.BAD_REQUEST);
        }
        User foundUser = userService.findByUsername(user.getUsername());
        if (foundUser != null && foundUser.getUsername().equals(user.getUsername())) {
            String userId = foundUser.getId().toString();
            return new ResponseEntity<>(userId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Get all Users", response = User.class, responseContainer = "List")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("userId") String userId) {
        if (!userService.isAdmin(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
