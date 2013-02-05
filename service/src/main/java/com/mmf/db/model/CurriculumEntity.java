package com.mmf.db.model;

import javax.persistence.*;

/**
 * User: admin
 * Date: 14.10.12
 */
@Entity
@Table(name = "curriculum")
public class CurriculumEntity implements EntityClass<Long>{
    private static final long serialVersionUID = 8490916118325046186L;
    private Long id;
    private Integer hours;
    private Integer semester;
    private Boolean exam;
    private Boolean setoff;
    private DisciplineEntity discipline;
    private SpecialtyEntity specialty;


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

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Boolean getExam() {
        return exam;
    }

    public void setExam(Boolean exam) {
        this.exam = exam;
    }

    public Boolean getSetoff() {
        return setoff;
    }

    public void setSetoff(Boolean setoff) {
        this.setoff = setoff;
    }

    @ManyToOne
    @JoinColumn(name = "IdDiscipline")
    public DisciplineEntity getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineEntity discipline) {
        this.discipline = discipline;
    }

    @ManyToOne
    @JoinColumn(name = "IdSpecialty")
    public SpecialtyEntity getSpecialty() {
        return specialty;
    }

    public void setSpecialty(SpecialtyEntity specialty) {
        this.specialty = specialty;
    }
}
