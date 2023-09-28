package com.example.TaskManager.services;
import com.example.TaskManager.entities.Task;
import com.example.TaskManager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTask() {
        // Arrange
        Task task = new Task();
        task.setTitle("Task Title");
        task.setDescription("Task Description");
        task.setStatus("Pending");
        task.setPriority("High");

        when(taskRepository.save(task)).thenReturn(task);

        // Act
        Task savedTask = taskService.saveTask(task);

        // Assert
        assertNotNull(savedTask.getId());
        assertEquals("Task Title", savedTask.getTitle());
    }

    @Test
    public void testGetTasks() {
        // Arrange
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "Task 1", "Description 1", "Pending", "High", new Date(), new Date(), "User1"));
        taskList.add(new Task(2, "Task 2", "Description 2", "In Progress", "Medium", new Date(), new Date(), "User2"));

        when(taskRepository.findAll()).thenReturn(taskList);

        // Act
        List<Task> tasks = taskService.getTasks();

        // Assert
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
    }

    @Test
    public void testGetTaskById() {
        // Arrange
        Task task = new Task(1, "Task 1", "Description 1", "Pending", "High", new Date(), new Date(), "User1");

        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        // Act
        Task foundTask = taskService.getTaskById(1);

        // Assert
        assertNotNull(foundTask);
        assertEquals("Task 1", foundTask.getTitle());
    }

    @Test
    public void testGetTaskByName() {
        // Arrange
        String taskName = "Task 1";
        Task task = new Task(1, taskName, "Description 1", "Pending", "High", new Date(), new Date(), "User1");

        when(taskRepository.findByTitle(taskName)).thenReturn(task);

        // Act
        Task foundTask = taskService.getTaskByName(taskName);

        // Assert
        assertNotNull(foundTask);
        assertEquals("Task 1", foundTask.getTitle());
    }

    @Test
    public void testGetTasksForUser() {
        // Arrange
        String username = "User1";
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "Task 1", "Description 1", "Pending", "High", new Date(), new Date(), username));

        when(taskRepository.findAllByUsername(username)).thenReturn(taskList);

        // Act
        List<Task> tasks = taskService.getTasksForUser(username);

        // Assert
        assertEquals(1, tasks.size());
        assertEquals("User1", tasks.get(0).getUsername());
    }

    @Test
    public void testGetPriorityTasksForUser() {
        // Arrange
        String username = "User1";
        String priority = "High";
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "Task 1", "Description 1", "Pending", "High", new Date(), new Date(), username));

        when(taskRepository.findAllByPriorityAndUsername(priority, username)).thenReturn(taskList);

        // Act
        List<Task> tasks = taskService.getPriorityTasksForUser(priority, username);

        // Assert
        assertEquals(1, tasks.size());
        assertEquals("High", tasks.get(0).getPriority());
    }

    @Test
    public void testUpdateTask() {
        // Arrange
        Task existingTask = new Task(1, "Task 1", "Description 1", "Pending", "High", new Date(), new Date(), "User1");
        Task updatedTask = new Task(1, "Updated Task", "Updated Description", "In Progress", "Medium", new Date(), new Date(), "User1");

        when(taskRepository.findById(1)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(updatedTask);

        // Act
        Task result = taskService.updateTask(updatedTask);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Task", result.getTitle());
    }

    @Test
    public void testDeleteTask() {
        // Arrange
        int taskId = 1;

        when(taskRepository.existsById(taskId)).thenReturn(true);

        // Act
        String result = taskService.deleteTask(taskId);

        // Assert
        assertEquals("DELETED", result);
    }
}
