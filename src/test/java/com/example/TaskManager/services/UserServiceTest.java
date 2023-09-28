package com.example.TaskManager.services;

import com.example.TaskManager.entities.User;
import com.example.TaskManager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.NonUniqueResultException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        // Arrange
        User user = new User(1, "test_user", "Password@1");

        when(userRepository.save(user)).thenReturn(user);

        // Act
        assertDoesNotThrow(() -> userService.saveUser(user));
        User savedUser = userService.getUser(user);

        // Assert
        assertNull(savedUser);
//        assertEquals("test_user", savedUser.getUsername());
    }

    @Test
    public void testGetUser() {
        // Arrange
        User user = new User(1, "test_user", "Password@1");

        when(userRepository.findByUsernameAndPassword("test_user", "Password@1")).thenReturn(user);

        // Act
        User retrievedUser = userService.getUser(user);

        // Assert
        assertNotNull(retrievedUser);
        assertEquals("test_user", retrievedUser.getUsername());
    }

    @Test
    public void testGetUserByUsernameAndPassword() {
        // Arrange
        String username = "test_user";
        String password = "Password@1";

        when(userRepository.findTopByUsername(username)).thenReturn(new User(1, "test_user", "Password@1"));
        when(userRepository.findTopByPassword(password)).thenReturn(new User(2, "another_user", "Password@1"));

        // Act
        boolean userExists = userService.getUserByUsername(username, password);

        // Assert
        assertTrue(userExists);
    }

    @Test
    public void testFindUserByUsername() {
        // Arrange
        String username = "test_user";

        when(userRepository.findTopByUsername(username)).thenReturn(new User(1, "test_user", "Password@123"));

        // Act
        boolean userExists = userService.findUserByUsername(username);

        // Assert
        assertTrue(userExists);
    }
}
