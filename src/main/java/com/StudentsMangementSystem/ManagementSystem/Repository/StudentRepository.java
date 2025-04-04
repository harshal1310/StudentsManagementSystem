package com.StudentsMangementSystem.ManagementSystem.Repository;

import com.StudentsMangementSystem.ManagementSystem.Entity.Course;
import com.StudentsMangementSystem.ManagementSystem.Entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Students,Long> {
    Optional<Students> findByUniqueCodeAndDateOfBirth(String uniqueCode, LocalDate dateOfBirth);
Optional<Students> findByUniqueCode(String uniqueCode);
    List<Students> findByName(String name);
    @Query("SELECT s FROM Students s JOIN s.courses c WHERE c.courseName = :courseName")
    List<Students> findByCourseName(String courseName);
}
