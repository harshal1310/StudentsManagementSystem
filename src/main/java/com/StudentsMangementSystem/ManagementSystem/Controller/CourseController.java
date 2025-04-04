package com.StudentsMangementSystem.ManagementSystem.Controller;

import com.StudentsMangementSystem.ManagementSystem.DTO.CourseDTO;
import com.StudentsMangementSystem.ManagementSystem.DTO.StudentDTO;
import com.StudentsMangementSystem.ManagementSystem.Service.CourseService;
import org.springframework.http.HttpStatus;
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
    @PostMapping("/{courseId}/enroll/{uniqueCode}")
    public ResponseEntity<String> enrollStudentInCourse(@PathVariable Long courseId, @PathVariable("uniqueCode") String studentId) {
        try {
            courseService.enrollStudentInCourse(studentId, courseId);
            return ResponseEntity.ok("Student enrolled in course successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    @DeleteMapping("/{courseId}/unenroll/{uniqueCode}")
    public ResponseEntity<String> unenrollStudentFromCourse(@PathVariable Long courseId, @PathVariable("uniqueCode") String studentId) {

        try{
            courseService.unenrollStudentFromCourse(studentId, courseId);

            return ResponseEntity.ok("Student unenrolled in course successfully");

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{uniqueCode}")
    public ResponseEntity<List<CourseDTO>> getCourseByName(@PathVariable("uniqueCode") String studentId) {
        return ResponseEntity.ok(courseService.getCourseByStudentId(studentId));
    }
    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByCourseId(@PathVariable Long courseId) {
        List<StudentDTO> students = courseService.getStudentsByCourseId(courseId);
        return ResponseEntity.ok(students);
    }
    @PutMapping("/update/{courseId}")
    public ResponseEntity<String> updateCourse(@PathVariable Long courseId, @RequestBody CourseDTO courseDTO) {
        try {
            courseService.updateCourse(courseId, courseDTO);
            return ResponseEntity.ok("Course updated successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
