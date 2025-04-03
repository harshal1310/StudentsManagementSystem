package com.StudentsMangementSystem.ManagementSystem.Service;

import com.StudentsMangementSystem.ManagementSystem.Entity.Admin;
import com.StudentsMangementSystem.ManagementSystem.Repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.StudentsMangementSystem.ManagementSystem.Entity.Course;
import com.StudentsMangementSystem.ManagementSystem.Entity.Students;
import com.StudentsMangementSystem.ManagementSystem.Repository.CourseRepository;
import com.StudentsMangementSystem.ManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder,
                        StudentRepository studentRepository, CourseRepository courseRepository) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    public Students addStudent(Students student) {
        return studentRepository.save(student);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public void assignCourseToStudent(Long studentId, Long courseId) {
        Students student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        student.getCourses().add(course);
        studentRepository.save(student);
    }




    public void registerAdmin(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRole("ADMIN");
        adminRepository.save(admin);
    }

    public boolean validateAdmin(String username, String password) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        return adminOpt.isPresent() && passwordEncoder.matches(password, adminOpt.get().getPassword());
    }

}
