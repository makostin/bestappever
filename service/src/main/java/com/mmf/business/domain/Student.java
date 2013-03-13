package com.mmf.business.domain;

/**
 * svetlana.voyteh
 * 13.03.13
 */
public class Student implements DomainClass<Long>{
    private static final long serialVersionUID = 8047233190001112585L;

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
