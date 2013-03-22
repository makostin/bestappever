package com.mmf.rest.domain;

import com.mmf.db.model.Department;
import com.mmf.db.model.Lecturer;
import com.mmf.db.model.Specialty;

import java.util.ArrayList;
import java.util.List;

/**
 * svetlana.voyteh
 * 14.03.13
 */
public class InitialData {

    private Integer courseAmount;
    private Integer groupAmount;
    private String firstSemesterStart;
    private String firstSemesterEnd;
    private String secondSemesterStart;
    private String secondSemesterEnd;
    private List<String> subGroups = new ArrayList<String>();
    private List<Department> departments = new ArrayList<Department>();
    private List<Specialty> specialties = new ArrayList<Specialty>();
    private List<Lecturer> lecturers = new ArrayList<Lecturer>();

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

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
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
