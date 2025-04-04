package com.StudentsMangementSystem.ManagementSystem.Repository;

import com.StudentsMangementSystem.ManagementSystem.DTO.CourseDTO;
import com.StudentsMangementSystem.ManagementSystem.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
   // @Query("SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId")
    //List<CourseDTO> findByStudentId(@Param("studentId") Long studentId);
    //@Query("SELECT c FROM Course c JOIN c.students s WHERE s.uniqueCode = :uniqueCode")
    //List<CourseDTO> findByStudentUniqueCode(@Param("uniqueCode") String uniqueCode);
    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.uniqueCode = :uniqueCode")
    List<Course> findByStudentUniqueCode(@Param("uniqueCode") String uniqueCode);

}
