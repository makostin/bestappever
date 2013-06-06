package com.mmf.rest.response;

import com.mmf.business.domain.Department;
import com.mmf.business.domain.SpecialtyInfo;
import com.mmf.rest.response.department.DepartmentResponse;

import java.util.List;

/**
 * svetlana.voyteh
 * 12.03.13
 */
public class InitialDataResponse {
    
    private Integer courseAmount;
    private Integer groupAmount;
    private String firstSemesterStart;
    private String firstSemesterEnd;
    private String secondSemesterStart;
    private String secondSemesterEnd;
    private List<String> subGroups;
    private List<DepartmentResponse> departments;
    private List<SpecialtyInfo> specialties;



    public Integer getCourseAmount() {
        return courseAmount;
    }

    public void setCourseAmount(Integer courseAmount) {
        this.courseAmount = courseAmount;
    }

    public Integer getGroupAmount() {
        return groupAmount;
    }

    public void setGroupAmount(Integer groupAmount) {
        this.groupAmount = groupAmount;
    }

    public List<String> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(List<String> subGroups) {
        this.subGroups = subGroups;
    }

    public List<DepartmentResponse> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentResponse> departments) {
        this.departments = departments;
    }

    public List<SpecialtyInfo> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<SpecialtyInfo> specialties) {
        this.specialties = specialties;
    }

    public String getFirstSemesterStart() {
        return firstSemesterStart;
    }

    public void setFirstSemesterStart(String firstSemesterStart) {
        this.firstSemesterStart = firstSemesterStart;
    }

    public String getFirstSemesterEnd() {
        return firstSemesterEnd;
    }

    public void setFirstSemesterEnd(String firstSemesterEnd) {
        this.firstSemesterEnd = firstSemesterEnd;
    }

    public String getSecondSemesterStart() {
        return secondSemesterStart;
    }

    public void setSecondSemesterStart(String secondSemesterStart) {
        this.secondSemesterStart = secondSemesterStart;
    }

    public String getSecondSemesterEnd() {
        return secondSemesterEnd;
    }

    public void setSecondSemesterEnd(String secondSemesterEnd) {
        this.secondSemesterEnd = secondSemesterEnd;
    }
}
