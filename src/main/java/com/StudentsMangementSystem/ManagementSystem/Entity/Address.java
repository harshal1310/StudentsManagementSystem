package com.StudentsMangementSystem.ManagementSystem.Entity;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String street;
    private String addressType;//permanet temporary
    private String city;
    private int pinCode;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Students student;
}
