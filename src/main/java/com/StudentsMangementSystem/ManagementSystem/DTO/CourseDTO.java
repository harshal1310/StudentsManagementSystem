package com.StudentsMangementSystem.ManagementSystem.DTO;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {
    private String courseName;
    private String courseType;
    private String courseDescription;

    public String getCourseName() {
        return courseName;
    }

    public CourseDTO(String courseName, String courseType, String courseDescription, String duration, String topics, List<StudentDTO> studentDTOList) {
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseDescription = courseDescription;
        this.duration = duration;
        this.topics = topics;
        this.studentDTOList = studentDTOList;
    }

    public CourseDTO() {
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public List<StudentDTO> getStudentDTOList() {
        return studentDTOList;
    }

    public void setStudentDTOList(List<StudentDTO> studentDTOList) {
        this.studentDTOList = studentDTOList;
    }

    private String duration;
    private String topics;
    List<StudentDTO> studentDTOList = new ArrayList<>();
}
