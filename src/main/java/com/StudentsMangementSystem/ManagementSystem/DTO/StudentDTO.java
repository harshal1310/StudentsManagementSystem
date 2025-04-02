package com.StudentsMangementSystem.ManagementSystem.DTO;

import com.StudentsMangementSystem.ManagementSystem.Entity.Address;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDTO {
    private String Name;
    private LocalDate DateOfBirth;
    private String Gender;
    private String uniqueCode;
    List<AddressDTO> addressDTOList = new ArrayList<>();
    List<CourseDTO> courseDTOList = new ArrayList<>();

    public StudentDTO() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public List<AddressDTO> getAddressDTOList() {
        return addressDTOList;
    }

    public void setAddressDTOList(List<AddressDTO> addressDTOList) {
        this.addressDTOList = addressDTOList;
    }

    public List<CourseDTO> getCourseDTOList() {
        return courseDTOList;
    }

    public void setCourseDTOList(List<CourseDTO> courseDTOList) {
        this.courseDTOList = courseDTOList;
    }
}
