package com.StudentsMangementSystem.ManagementSystem.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String courseName;
    private String courseType;
    private String courseDescription;
    private String duration;
    private String topics;

    @ManyToMany(mappedBy = "courses")
    private List<Students> students = new ArrayList<>();
}
