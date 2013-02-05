package com.mmf.db.model;

import javax.persistence.*;

/**
 * @author svetlana.voyteh
 * @date: 5/11/12
 */
@Entity
@Table(name = "discipline")
public class DisciplineEntity implements EntityClass<Long>{

    private static final long serialVersionUID = -3771558372410085731L;
    private Long id;
    private String name;
    private DisciplineTypeEntity disciplineType;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "IdDisciplineType")
    public DisciplineTypeEntity getDisciplineType() {
        return disciplineType;
    }

    public void setDisciplineType(DisciplineTypeEntity disciplineType) {
        this.disciplineType = disciplineType;
    }
}
