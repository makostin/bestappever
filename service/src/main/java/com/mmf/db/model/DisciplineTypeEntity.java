package com.mmf.db.model;

import javax.persistence.*;

/**
 * svetlana.voyteh
 * 05.02.13
 */
@Entity
@Table(name = "disciplineType")
public class DisciplineTypeEntity implements EntityClass<Long>{
    private static final long serialVersionUID = 548135069778009258L;
    private Long id;
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
