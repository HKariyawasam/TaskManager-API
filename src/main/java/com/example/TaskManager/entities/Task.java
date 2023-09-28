package com.example.TaskManager.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String description;
    private String status;
    private String priority;
    private Date createdAt;
    private Date updatedAt;

    @Column(name= "username")
    private String username;

}
