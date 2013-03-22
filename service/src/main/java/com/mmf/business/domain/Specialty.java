package com.mmf.business.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * svetlana.voyteh
 * 05.03.13
 */
public class Specialty implements DomainClass<Long>{

    private static final long serialVersionUID = 6500114774058913637L;

    private Long id;
    private String name;
    private String description;
    private Set<Group> groups = new HashSet<Group>();

    public Specialty(){}

    public Specialty(Long id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
