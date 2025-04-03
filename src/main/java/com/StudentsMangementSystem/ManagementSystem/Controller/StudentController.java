package com.StudentsMangementSystem.ManagementSystem.Controller;

import com.StudentsMangementSystem.ManagementSystem.DTO.StudentDTO;
import com.StudentsMangementSystem.ManagementSystem.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.addStudent(studentDTO));
    }

    @GetMapping("/studentsByName")
    public ResponseEntity<List<StudentDTO>> getStudentsByName(@RequestParam String name) {
        return ResponseEntity.ok(studentService.getStudentsByName(name));
    }
    @GetMapping("/studentsByCourseName")
    public ResponseEntity<List<StudentDTO>> getStudentsByCourseName(@RequestParam String courseName) {
        return ResponseEntity.ok(studentService.getStudentsByCourseName(courseName));
    }

    @PostMapping("/{studentId}/assign-course/{courseId}")
    public ResponseEntity<StudentDTO> assignCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.assignCourseToStudent(studentId, courseId));
    }
}
