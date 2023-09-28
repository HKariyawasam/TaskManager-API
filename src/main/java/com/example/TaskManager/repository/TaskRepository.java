package com.example.TaskManager.repository;


import com.example.TaskManager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findByTitle(String title);

    List<Task> findAllByUsername(String username);

    List<Task> findAllByPriorityAndUsername(String priority,String username);

}
