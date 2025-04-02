package com.StudentsMangementSystem.ManagementSystem.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Name;
    private LocalDate DateOfBirth;
    private String Gender;
    @Column(unique = true)
    private String uniqueCode;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();
}
