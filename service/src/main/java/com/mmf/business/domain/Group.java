package com.mmf.business.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * svetlana.voyteh
 * 12.03.13
 */
public class Group implements DomainClass<Long> {
    private static final long serialVersionUID = 4370536111918193061L;

    private Long id;
    private Integer number;
    private Integer course;
    private String subgroup;
    private Integer year;
    private Specialty specialty;
    private Set<Student> students = new HashSet<Student>();

    public Group(){}

    public Group(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }
}
