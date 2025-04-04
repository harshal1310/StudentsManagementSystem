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
    private final StudentService studentService;

    public CourseService(CourseRepository courseRepository,StudentRepository studentRepository,StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;

    }

    public CourseDTO addCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseDescription(courseDTO.getCourseDescription());
        course.setCourseType(courseDTO.getCourseType());
        course.setDuration(courseDTO.getDuration());
        course.setTopics(courseDTO.getTopics());

        Course savedCourse = courseRepository.save(course);
        return mapCourseToDTO(savedCourse);
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapCourseToDTO)
                .collect(Collectors.toList());
    }

    public void unenrollStudentFromCourse(String studentId, Long courseId) {
        Optional<Students> studentOpt = studentRepository.findByUniqueCode(studentId);
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
    public void updateCourse(Long courseId, CourseDTO courseDTO) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            course.setCourseName(courseDTO.getCourseName());
            course.setCourseType(courseDTO.getCourseType());
            course.setCourseDescription(courseDTO.getCourseDescription());
            course.setDuration(courseDTO.getDuration());
            course.setTopics(courseDTO.getTopics());
            courseRepository.save(course);
        } else {
            throw new RuntimeException("Course not found");
        }
    }

   public List<CourseDTO> getCourseByStudentId(String studentId) {
        List<Course> courses = courseRepository.findByStudentUniqueCode(studentId);
        if (courses == null) {
            throw new RuntimeException("Course not found");
        }
       return courses.stream()
               .map(this::mapCourseToDTO)
               .collect(Collectors.toList());
    }



    public void enrollStudentInCourse(String studentId, Long courseId) {
        Optional<Students> studentOpt = studentRepository.findByUniqueCode(studentId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (!studentOpt.isPresent()) {
            throw new RuntimeException("Student with unique code " + studentId + " not found");
        }

        if (!courseOpt.isPresent()) {
            throw new RuntimeException("Course with ID " + courseId + " not found");
        }

        Students student = studentOpt.get();
        Course course = courseOpt.get();

        student.getCourses().add(course);
        course.getStudents().add(student); // Ensure the course also references the student
        studentRepository.save(student);
        courseRepository.save(course); // Save the course to update the relationship
    }
    public List<StudentDTO> getStudentsByCourseId(Long courseId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (!courseOpt.isPresent()) {
            throw new RuntimeException("Course with ID " + courseId + " not found");
        }

        Course course = courseOpt.get();
        return course.getStudents().stream()
                .map( student -> studentService.mapStudentToDTO(student))
                .collect(Collectors.toList());
    }

    private CourseDTO mapCourseToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setCourseName(course.getCourseName());
        dto.setCourseType(course.getCourseType());
        dto.setCourseDescription(course.getCourseDescription());
        dto.setDuration(course.getDuration());
        dto.setTopics(course.getTopics());
        return dto;
    }
}
