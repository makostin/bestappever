package com.mmf.rest.response;

import com.mmf.business.domain.Department;
import com.mmf.business.domain.SpecialtyInfo;

import java.util.List;

/**
 * svetlana.voyteh
 * 12.03.13
 */
public class InitialDataResponse {
    
    private Integer courseAmount;
    private Integer groupAmount;
    private List<String> subGroups;
    private List<Department> departments;
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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<SpecialtyInfo> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<SpecialtyInfo> specialties) {
        this.specialties = specialties;
    }
}
