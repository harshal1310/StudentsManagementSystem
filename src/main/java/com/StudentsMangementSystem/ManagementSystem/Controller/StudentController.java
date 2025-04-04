package com.StudentsMangementSystem.ManagementSystem.Controller;

import com.StudentsMangementSystem.ManagementSystem.DTO.StudentDTO;
import com.StudentsMangementSystem.ManagementSystem.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@Valid @RequestBody StudentDTO studentDTO) {

        try {
            studentService.addStudent(studentDTO);
            return ResponseEntity.ok("Student added successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }    }

    @GetMapping("/studentsByName")
    public ResponseEntity<List<StudentDTO>> getStudentsByName(@RequestParam String name) {
        return ResponseEntity.ok(studentService.getStudentsByName(name));
    }
    @GetMapping("/studentsByCourseName")
    public ResponseEntity<List<StudentDTO>> getStudentsByCourseName(@RequestParam String courseName) {
        return ResponseEntity.ok(studentService.getStudentsByCourseName(courseName));
    }



    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyStudentIdentity(@RequestParam String studentCode, @RequestParam String dateOfBirth) {
        LocalDate dob = LocalDate.parse(dateOfBirth);
        boolean isVerified = studentService.verifyStudentIdentity(studentCode, dob);
        return ResponseEntity.ok(isVerified);
    }


    @PutMapping("/update/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO) {
        try {
            studentService.updateStudent(studentId, studentDTO);
            return ResponseEntity.ok("Student updated successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
