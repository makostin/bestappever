package com.mmf.business.domain;

/**
 * svetlana.voyteh
 * 05.03.13
 */
public class Specialty implements DomainClass<Long>{

    private static final long serialVersionUID = 6500114774058913637L;

    private Long id;
    private String name;
    private String description;

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
}