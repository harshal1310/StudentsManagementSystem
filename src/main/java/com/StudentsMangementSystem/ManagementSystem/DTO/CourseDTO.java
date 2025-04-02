package com.StudentsMangementSystem.ManagementSystem.DTO;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {
    private String courseName;
    private String courseType;
    private String courseDescription;
    private String duration;
    private String topics;
    List<StudentDTO> studentDTOList = new ArrayList<>();
}
