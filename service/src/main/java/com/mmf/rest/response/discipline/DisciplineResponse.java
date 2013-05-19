package com.mmf.rest.response.discipline;


import com.mmf.business.domain.Discipline;
import com.mmf.business.domain.DisciplineType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author svetlana.voyteh
 * @date: 5/11/12
 */
@XmlRootElement
public class DisciplineResponse {

    private Long id;
    private String name;
    private DisciplineType disciplineType;

    public DisciplineResponse() {
    }

    public DisciplineResponse(Discipline discipline) {
        this.id = discipline.getId();
        this.name = discipline.getName();
        this.disciplineType = discipline.getDisciplineType();
    }

    public DisciplineResponse(Long id) {
        this.id = id;
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

    public DisciplineType getDisciplineType() {
        return disciplineType;
    }

    public void setDisciplineType(DisciplineType disciplineType) {
        this.disciplineType = disciplineType;
    }

}
