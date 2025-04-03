package com.StudentsMangementSystem.ManagementSystem.Service;

import com.StudentsMangementSystem.ManagementSystem.DTO.CourseDTO;
import com.StudentsMangementSystem.ManagementSystem.DTO.StudentDTO;
import com.StudentsMangementSystem.ManagementSystem.Entity.Course;
import com.StudentsMangementSystem.ManagementSystem.Entity.Students;
import com.StudentsMangementSystem.ManagementSystem.Repository.CourseRepository;
import com.StudentsMangementSystem.ManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    public CourseService(CourseRepository courseRepository,StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public CourseDTO addCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseDescription(courseDTO.getCourseDescription());
        course.setCourseType(courseDTO.getCourseType());
        course.setDuration(courseDTO.getDuration());
        course.setTopics(courseDTO.getTopics());

        Course savedCourse = courseRepository.save(course);
        return mapToDTO(savedCourse);
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void unenrollStudentFromCourse(Long studentId, Long courseId) {
        Optional<Students> studentOpt = studentRepository.findById(studentId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            Students student = studentOpt.get();
            Course course = courseOpt.get();

            student.getCourses().remove(course);
            studentRepository.save(student);
        } else {
            throw new RuntimeException("Student or Course not found");
        }
    }

   public List<CourseDTO> getCourseByStudentId(long studentId) {
        List<CourseDTO> courseDTO = courseRepository.findByStudentId(studentId);
        if (courseDTO == null) {
            throw new RuntimeException("Course not found");
        }
        return courseDTO;
    }

    public void enrollStudentInCourse(Long studentId, Long courseId) {
        var course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        var student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        course.getStudents().add(student);
        courseRepository.save(course);
    }

    private CourseDTO mapToDTO (Course course){

        CourseDTO dto = new CourseDTO();
        dto.setCourseName(dto.getCourseName());
        dto.setCourseType(dto.getCourseType());
        dto.setCourseDescription(dto.getCourseDescription());
        dto.setDuration(dto.getDuration());
        dto.setTopics(dto.getTopics());
        return dto;
    }
}
