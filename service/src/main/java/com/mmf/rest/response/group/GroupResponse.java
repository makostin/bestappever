package com.mmf.rest.response.group;

import com.mmf.business.domain.Group;
import com.mmf.business.domain.Specialty;
import com.mmf.business.domain.Student;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 19.05.13
 */
@XmlRootElement
public class GroupResponse {

    private Long id;
    private Integer number;
    private Integer course;
    private String subgroup;
    private Integer year;
    private Specialty specialty;
    private List<Student> students = new LinkedList<Student>();

    public GroupResponse() {
    }

    public GroupResponse(Group group) {
        this.id = group.getId();
        this.number = group.getNumber();
        this.course = group.getCourse();
        this.subgroup = group.getSubgroup();
        this.year = group.getYear();
        this.specialty = group.getSpecialty();
        this.students.addAll(group.getStudents());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
