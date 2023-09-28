package com.example.TaskManager.controllers;
import com.example.TaskManager.entities.Task;
import com.example.TaskManager.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTask() {
        // Arrange
        Task task = new Task(1, "Task 1", "Description", "Pending", "High", null, null, "User1");
        when(taskService.saveTask(task)).thenReturn(task);

        // Act
        ResponseEntity<Task> responseEntity = taskController.create(task);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Task 1", responseEntity.getBody().getTitle());
    }

    @Test
    public void testGetAllTasks() {
        // Arrange
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "Task 1", "Description 1", "Pending", "High", null, null, "User1"));
        taskList.add(new Task(2, "Task 2", "Description 2", "In Progress", "Medium", null, null, "User2"));

        when(taskService.getTasks()).thenReturn(taskList);

        // Act
        ResponseEntity<List<Task>> responseEntity = taskController.getAllTasks();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(2, responseEntity.getBody().size());
        assertEquals("Task 1", responseEntity.getBody().get(0).getTitle());
    }

    @Test
    public void testFindTaskById() {
        // Arrange
        Task task = new Task(1, "Task 1", "Description 1", "Pending", "High", null, null, "User1");
        when(taskService.getTaskById(1)).thenReturn(task);

        // Act
        ResponseEntity<Task> responseEntity = taskController.findTaskById(1);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Task 1", responseEntity.getBody().getTitle());
    }

    @Test
    public void testFindTaskByName() {
        // Arrange
        Task task = new Task(1, "Task 1", "Description 1", "Pending", "High", null, null, "User1");
        when(taskService.getTaskByName("Task 1")).thenReturn(task);

        // Act
        ResponseEntity<Task> responseEntity = taskController.findTaskByName("Task 1");

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Task 1", responseEntity.getBody().getTitle());
    }

    @Test
    public void testFindTasksByUsername() {
        // Arrange
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "Task 1", "Description 1", "Pending", "High", null, null, "User1"));
        when(taskService.getTasksForUser("User1")).thenReturn(taskList);

        // Act
        ResponseEntity<List<Task>> responseEntity = taskController.findTasksByUsername("User1");

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals("User1", responseEntity.getBody().get(0).getUsername());
    }

    @Test
    public void testFindTasksByUsernameAndPriority() {
        // Arrange
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "Task 1", "Description 1", "Pending", "High", null, null, "User1"));
        when(taskService.getPriorityTasksForUser("High", "User1")).thenReturn(taskList);

        // Act
        ResponseEntity<List<Task>> responseEntity = taskController.findTasksByUsernameAndPriority("High", "User1");

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals("High", responseEntity.getBody().get(0).getPriority());
    }

    @Test
    public void testUpdateTask() {
        // Arrange
        Task updatedTask = new Task(1, "Updated Task", "Updated Description", "In Progress", "Medium", null, null, "User1");
        when(taskService.updateTask(updatedTask)).thenReturn(updatedTask);

        // Act
        ResponseEntity<Task> responseEntity = taskController.update(updatedTask);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Updated Task", responseEntity.getBody().getTitle());
    }

    @Test
    public void testDeleteTask() {
        // Arrange
        when(taskService.deleteTask(1)).thenReturn("Task deleted successfully");

        // Act
        ResponseEntity<String> responseEntity = taskController.delete(1);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Failed to delete task", responseEntity.getBody());
    }


}
