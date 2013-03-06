package com.mmf.business.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * svetlana.voyteh
 * 05.03.13
 */
public class Department implements DomainClass<Long>{
    private static final long serialVersionUID = 935713329842117590L;

    private Long id;
    private String name;
    private String description;
    private Set<Lecturer> lecturers = new HashSet<Lecturer>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }
}
