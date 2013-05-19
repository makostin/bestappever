package com.mmf.rest.response.specialty;

import com.mmf.business.domain.Group;
import com.mmf.business.domain.Specialty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 19.05.13
 */
@XmlRootElement
public class SpecialtyResponse {

    private Long id;
    private String name;
    private String description;
    private List<Group> groups = new LinkedList<Group>();

    public SpecialtyResponse() {
    }

    public SpecialtyResponse(Specialty domain) {
        this.id = domain.getId();
        this.name = domain.getName();
        this.description = domain.getDescription();
        this.groups.addAll(domain.getGroups());
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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
