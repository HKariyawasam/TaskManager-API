package com.example.TaskManager.controllers;
import com.example.TaskManager.entities.Task;
import com.example.TaskManager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


    // POST
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task createdTask = taskService.saveTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // GET all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // GET task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable int id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET task by name
    @GetMapping("/title/{title}")
    public ResponseEntity<Task> findTaskByName(@PathVariable String title) {
        Task task = taskService.getTaskByName(title);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET tasks by username
    @GetMapping("/user/name/{username}")
    public ResponseEntity<List<Task>> findTasksByUsername(@PathVariable String username) {
        List<Task> tasks = taskService.getTasksForUser(username);
        if (!tasks.isEmpty()) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT
    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {
        Task updatedTask = taskService.updateTask(task);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        String result = taskService.deleteTask(id);
        System.out.println(result);
        if (result.equals("DELETED")) {
            return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete task", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

