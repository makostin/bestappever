package com.mmf.db.model;

import javax.persistence.*;

/**
 * @author svetlana.voyteh
 * @date: 5/11/12
 */
@Entity
@Table(name = "classroom")
public class ClassroomEntity implements EntityClass<Long>{

    private static final long serialVersionUID = 1663586800457756747L;
    private Long id;
    private String number;
    private Integer capacity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
