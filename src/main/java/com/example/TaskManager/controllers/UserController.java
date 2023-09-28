package com.example.TaskManager.controllers;

import com.example.TaskManager.entities.User;
import com.example.TaskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User loggedInUser = userService.getUser(user);

        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 Unauthorized
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
        if (userService.findUserByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false); // 409 Conflict, User already exists
        }

        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(true); // 201 Created
    }

    @GetMapping("/path/login")
    public ResponseEntity<Boolean> findUserByUsernameAndPassword(
            @RequestParam String username, @RequestParam String password) {
        boolean userExists = userService.getUserByUsername(username, password);

        if (userExists) {
            return ResponseEntity.ok(true); // User exists
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false); // Unauthorized
        }
    }


}
