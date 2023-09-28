package com.example.TaskManager.services;

import com.example.TaskManager.entities.Task;
import com.example.TaskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    //Saving a newly created task
    public Task saveTask(Task task) {
        try {
            if (task == null) {
                throw new IllegalArgumentException("Task cannot be null.");
            }

            Date createdAt = new Date();
            task.setCreatedAt(createdAt);
            task.setUpdatedAt(createdAt);
            Task savedTask = taskRepository.save(task);

            return savedTask;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save task: " + e.getMessage(), e);
        }
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    // Retrieve the details of a course using id
    public Task getTaskById(int id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("Invalid task ID");
            }

            Optional<Task> task = taskRepository.findById(id);
            return task.orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve task by ID: " + e.getMessage(), e);
        }
    }

    // Retrieve the details of a course using title
    public Task getTaskByName(String name) {
        try {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Task name cannot be empty.");
            }

            return taskRepository.findByTitle(name);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve task by name: " + e.getMessage(), e);
        }
    }

    // Retrieve all the tasks respective to a specific user
    public List<Task> getTasksForUser(String username) {
        try {
            if (username == null || username.isEmpty()) {
                throw new IllegalArgumentException("Username cannot be empty.");
            }

            return taskRepository.findAllByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve tasks for user: " + e.getMessage(), e);
        }
    }

    // Update a task
    public Task updateTask(Task task) {
        try {
            if (task == null || task.getId() <= 0) {
                throw new IllegalArgumentException("Invalid task or task ID.");
            }

            Optional<Task> existingTask = taskRepository.findById(task.getId());
            if (!existingTask.isPresent()) {
                throw new RuntimeException("Task not found.");
            }

            Task existingTaskEntity = existingTask.get();
            existingTaskEntity.setTitle(task.getTitle());
            existingTaskEntity.setDescription(task.getDescription());
            existingTaskEntity.setStatus(task.getStatus());
            existingTaskEntity.setUpdatedAt(new Date());

            return taskRepository.save(existingTaskEntity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update task: " + e.getMessage(), e);
        }
    }

    // Delete a task
    public String deleteTask(int id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("Invalid task ID.");
            }

            if (!taskRepository.existsById(id)) {
                throw new RuntimeException("Task not found.");
            }

            taskRepository.deleteById(id);
            return "DELETED";
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete task: " + e.getMessage(), e);
        }
    }
}
