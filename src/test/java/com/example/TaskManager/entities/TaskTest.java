package com.example.TaskManager.entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskTest {

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task();
    }

    @Test
    public void testIdGetterAndSetter() {
        // Arrange
        int id = 1;

        // Act
        task.setId(id);
        int result = task.getId();

        // Assert
        assertEquals(id, result);
        assertNotNull(task.getId());
    }

    @Test
    public void testTitleGetterAndSetter() {
        // Arrange
        String title = "Task Title";

        // Act
        task.setTitle(title);
        String result = task.getTitle();

        // Assert
        assertEquals(title, result);
        assertNotNull(task.getTitle());
    }

    @Test
    public void testDescriptionGetterAndSetter() {
        // Arrange
        String description = "Task Description";

        // Act
        task.setDescription(description);
        String result = task.getDescription();

        // Assert
        assertEquals(description, result);
        assertNotNull(task.getDescription());
    }

    @Test
    public void testStatusGetterAndSetter() {
        // Arrange
        String status = "In Progress";

        // Act
        task.setStatus(status);
        String result = task.getStatus();

        // Assert
        assertEquals(status, result);
        assertNotNull(task.getStatus());
    }

    @Test
    public void testPriorityGetterAndSetter() {
        // Arrange
        String priority = "High";

        // Act
        task.setPriority(priority);
        String result = task.getPriority();

        // Assert
        assertEquals(priority, result);
        assertNotNull(task.getPriority());
    }

    @Test
    public void testCreatedAtGetterAndSetter() {
        // Arrange
        Date createdAt = new Date();

        // Act
        task.setCreatedAt(createdAt);
        Date result = task.getCreatedAt();

        // Assert
        assertEquals(createdAt, result);
        assertNotNull(task.getCreatedAt());
    }

    @Test
    public void testUpdatedAtGetterAndSetter() {
        // Arrange
        Date updatedAt = new Date();

        // Act
        task.setUpdatedAt(updatedAt);
        Date result = task.getUpdatedAt();

        // Assert
        assertEquals(updatedAt, result);
        assertNotNull(task.getUpdatedAt());
    }

    @Test
    public void testUsernameGetterAndSetter() {
        // Arrange
        String username = "test_user";

        // Act
        task.setUsername(username);
        String result = task.getUsername();

        // Assert
        assertEquals(username, result);
        assertNotNull(task.getUsername());
    }
}
