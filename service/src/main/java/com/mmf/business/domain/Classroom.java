package com.mmf.business.domain;


import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author svetlana.voyteh
 * @date: 5/11/12
 */
@XmlRootElement
public class Classroom implements DomainClass<Long>{

    private static final long serialVersionUID = 2413512151514903598L;
    private Long id;
    private String number;
    private Integer capacity;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
