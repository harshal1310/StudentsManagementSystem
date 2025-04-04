package com.StudentsMangementSystem.ManagementSystem.Service;

import com.StudentsMangementSystem.ManagementSystem.DTO.CourseDTO;
import com.StudentsMangementSystem.ManagementSystem.DTO.StudentDTO;
import com.StudentsMangementSystem.ManagementSystem.Entity.Course;
import com.StudentsMangementSystem.ManagementSystem.Entity.Students;
import com.StudentsMangementSystem.ManagementSystem.Repository.CourseRepository;
import com.StudentsMangementSystem.ManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
       /* Students student = new Students();

        student.setName(studentDTO.getName());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setGender(studentDTO.getGender());
        student.setUniqueCode(studentDTO.getUniqueCode());

        Students savedStudent = studentRepository.save(student);
        return mapToDTO(savedStudent);*/
        try {
            Students student = new Students();
            student.setDateOfBirth(studentDTO.getDateOfBirth());
            student.setEmail(studentDTO.getEmail());
            student.setGender(studentDTO.getGender());
            student.setName(studentDTO.getName());
            student.setNumber(studentDTO.getNumber());
            student.setParentsName(studentDTO.getParentsName());
            student.setPassword(studentDTO.getPassword());
            student.setUniqueCode(studentDTO.getUniqueCode());
            studentRepository.save(student);
            return mapToDTO(student);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("A student with this unique code already exists.");
        }
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
    public boolean verifyStudentIdentity(String studentCode, LocalDate dateOfBirth) {
        Optional<Students> student = studentRepository.findByUniqueCodeAndDateOfBirth(studentCode, dateOfBirth);
        return student.isPresent();
    }

    public void updateStudent(Long studentId, StudentDTO studentDTO) {
        Optional<Students> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isPresent()) {
            Students student = studentOpt.get();
            student.setName(studentDTO.getName());
            student.setDateOfBirth(studentDTO.getDateOfBirth());
            student.setGender(studentDTO.getGender());
            student.setEmail(studentDTO.getEmail());
            student.setNumber(studentDTO.getNumber());
            student.setParentsName(studentDTO.getParentsName());
            student.setPassword(studentDTO.getPassword());
            student.setUniqueCode(studentDTO.getUniqueCode());
            studentRepository.save(student);
        } else {
            throw new RuntimeException("Student not found");
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
