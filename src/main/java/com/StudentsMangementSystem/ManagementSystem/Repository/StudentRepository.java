package com.StudentsMangementSystem.ManagementSystem.Repository;

import com.StudentsMangementSystem.ManagementSystem.Entity.Course;
import com.StudentsMangementSystem.ManagementSystem.Entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Students,Long> {
    List<Students> findByName(String name);
    @Query("SELECT s FROM Students s JOIN s.courses c WHERE c.courseName = :courseName")
    List<Students> findByCourseName(String courseName);
}
