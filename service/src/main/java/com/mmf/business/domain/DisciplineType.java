package com.mmf.business.domain;


/**
 * svetlana.voyteh
 * 05.02.13
 */
public class DisciplineType implements DomainClass<Long>{
    private static final long serialVersionUID = -5064291874486669667L;

    private Long id;
    private String type;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
