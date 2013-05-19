package com.mmf.business.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * svetlana.voyteh
 * 12.03.13
 */
@XmlRootElement
public class Group implements DomainClass<Long> {
    private static final long serialVersionUID = 4370536111918193061L;

    private Long id;
    private Integer number;
    private Integer course;
    private String subgroup;
    private Integer year;
    private Long specialtyId;
    private Specialty specialty;
    private List<Student> students = new LinkedList<Student>();

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

    @XmlTransient
    public Specialty getSpecialty() {
        return specialty;
    }

    public Integer getNumber() {
        return number;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    @XmlTransient
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }
}
