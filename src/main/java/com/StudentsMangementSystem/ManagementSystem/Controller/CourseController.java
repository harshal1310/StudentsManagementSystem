package com.StudentsMangementSystem.ManagementSystem.Controller;

import com.StudentsMangementSystem.ManagementSystem.DTO.CourseDTO;
import com.StudentsMangementSystem.ManagementSystem.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody CourseDTO courseDTO) {
        courseService.addCourse(courseDTO);
        return ResponseEntity.ok("Course added successfully");
    }
    @PostMapping("/{courseId}/enroll/{studentId}")
    public ResponseEntity<String> enrollStudentInCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        courseService.enrollStudentInCourse(studentId, courseId);
        return ResponseEntity.ok("Student enrolled in course successfully");
    }
    @DeleteMapping("/{courseId}/unenroll/{studentId}")
    public ResponseEntity<Void> unenrollStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        courseService.unenrollStudentFromCourse(studentId, courseId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/all")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<CourseDTO>> getCourseByName(@PathVariable long studentId) {
        return ResponseEntity.ok(courseService.getCourseByStudentId(studentId));
    }
}
