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
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    public StudentDTO addStudent(StudentDTO studentDTO) {
        Students student = new Students();

        student.setName(studentDTO.getName());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setGender(studentDTO.getGender());
        student.setUniqueCode(studentDTO.getUniqueCode());

        Students savedStudent = studentRepository.save(student);
        return mapToDTO(savedStudent);
    }

    public List<StudentDTO> getStudentsByName(String name) {
        List<Students> students = studentRepository.findByName(name);
        return students.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public List<StudentDTO> getStudentsByCourseName(String coursename) {
        List<Students>students =  studentRepository.findByCourseName(coursename);
             return students.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO assignCourseToStudent(Long studentId, Long courseId) {
        Optional<Students> studentOpt = studentRepository.findById(studentId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            Students student = studentOpt.get();
            Course course = courseOpt.get();

            student.getCourses().add(course);
            studentRepository.save(student);

            return mapToDTO(student);
        } else {
            throw new RuntimeException("Student or Course not found");
        }
    }


        private StudentDTO mapToDTO (Students student){
            StudentDTO dto = new StudentDTO();
            dto.setName(student.getName());
            dto.setDateOfBirth(student.getDateOfBirth());
            dto.setGender(student.getGender());
            dto.setUniqueCode(student.getUniqueCode());
            return dto;
        }

}
