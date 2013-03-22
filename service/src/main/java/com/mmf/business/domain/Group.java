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
    private String name;
    private Integer year;
    private Specialty specialty;
    private Group mainGroup;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Group getMainGroup() {
        return mainGroup;
    }

    public void setMainGroup(Group mainGroup) {
        this.mainGroup = mainGroup;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
