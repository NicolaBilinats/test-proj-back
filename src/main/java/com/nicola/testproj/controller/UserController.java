package com.nicola.testproj.controller;

import com.nicola.testproj.model.User;
import com.nicola.testproj.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@OpenAPIDefinition(
        info = @Info(
                title = "Users API",
                version = "1.0",
                description = "Operations for managing users"
        )
)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(summary = "Create new user", description = "Create new user in the database.")
    public ResponseEntity<User> register(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.registerUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
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

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user list"),
            @ApiResponse(responseCode = "404", description = "Users not found")
    })
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("userId") String userId) {
        if (!userService.isAdmin(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/count_users_with_answers")
    public ResponseEntity<Integer> getCountUsersWithAnswers(@RequestHeader("userId") String userId) {
        if (!userService.isAdmin(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(userService.getCountUsersWithAnswers());
    }

    @GetMapping("/count_users_with_all_answers")
    public ResponseEntity<Integer> getCountUsersWithAllAnswers(@RequestHeader("userId") String userId) {
        if (!userService.isAdmin(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(userService.getCountUsersWithAllAnswers());
    }

    @GetMapping("/count_users_with_all_correct_answers")
    public ResponseEntity<Integer> getCountUsersWithAllCorrectAnswers(@RequestHeader("userId") String userId) {
        if (!userService.isAdmin(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(userService.getCountUsersWithAllCorrectAnswers());
    }

    @GetMapping("/percent_correct_answer_by_user")
    public ResponseEntity<Double> getPercentCorrectAnswersByUser(@RequestHeader("userId") String userId) {
        return ResponseEntity.ok(userService.getPercentOfCorrectAnswersByUser(userId));
    }

}
