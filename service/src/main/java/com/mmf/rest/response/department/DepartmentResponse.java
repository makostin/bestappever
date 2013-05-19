package com.mmf.rest.response.department;

import com.mmf.business.domain.Department;
import com.mmf.business.domain.Lecturer;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 19.05.13
 */
@XmlRootElement
public class DepartmentResponse {

    private Long id;
    private String name;
    private String description;
    private List<Lecturer> lecturers = new LinkedList<Lecturer>();

    public DepartmentResponse() {
    }

    public DepartmentResponse(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.description = department.getDescription();
        this.lecturers.addAll(department.getLecturers());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }
}
