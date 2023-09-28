package com.example.TaskManager.controllers;
import com.example.TaskManager.entities.User;
import com.example.TaskManager.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginUserValidUser() {
        // Mock the behavior of userService.getUser
        User user = new User(1, "testUser", "password");
        when(userService.getUser(any(User.class))).thenReturn(user);

        // Act
        ResponseEntity<User> responseEntity = userController.loginUser(new User());

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void testLoginUserInvalidUser() {
        // Mock the behavior of userService.getUser
        when(userService.getUser(any(User.class))).thenReturn(null);

        // Act
        ResponseEntity<User> responseEntity = userController.loginUser(new User());

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testCreateUserUserCreated() {
        // Mock the behavior of userService.findUserByUsername
        when(userService.findUserByUsername(anyString())).thenReturn(false);

        // Act
        ResponseEntity<Boolean> responseEntity = userController.createUser(new User());

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody());
    }

    @Test
    public void testFindUserByUsernameAndPasswordUserExists() {
        // Mock the behavior of userService.getUserByUsername
        when(userService.getUserByUsername(anyString(), anyString())).thenReturn(true);

        // Act
        ResponseEntity<Boolean> responseEntity = userController.findUserByUsernameAndPassword("testUser", "password");

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody());
    }

    @Test
    public void testFindUserByUsernameAndPasswordUserNotExists() {
        // Mock the behavior of userService.getUserByUsername
        when(userService.getUserByUsername(anyString(), anyString())).thenReturn(false);

        // Act
        ResponseEntity<Boolean> responseEntity = userController.findUserByUsernameAndPassword("testUser", "password");

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody());
    }
}
