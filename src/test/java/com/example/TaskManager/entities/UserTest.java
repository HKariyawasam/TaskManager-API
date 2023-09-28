package com.example.TaskManager.entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testIdGetterAndSetter() {
        // Arrange
        int id = 1;

        // Act
        user.setId(id);
        int result = user.getId();

        // Assert
        assertEquals(id, result);
        assertNotNull(user.getId());
    }

    @Test
    public void testUsernameGetterAndSetter() {
        // Arrange
        String username = "test_user";

        // Act
        user.setUsername(username);
        String result = user.getUsername();

        // Assert
        assertEquals(username, result);
        assertNotNull(user.getUsername());
    }

    @Test
    public void testPasswordGetterAndSetter() {
        // Arrange
        String password = "secret_password";

        // Act
        user.setPassword(password);
        String result = user.getPassword();

        // Assert
        assertEquals(password, result);
        assertNotNull(user.getPassword());
    }
}
