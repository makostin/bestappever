package com.mmf.business.domain;


/**
 * User: admin
 * Date: 14.10.12
 */
public class Curriculum implements DomainClass<Long>{
    private static final long serialVersionUID = -6007177809701217399L;

    private Long id;
    private Integer hours;
    private Integer semester;
    private Boolean exam;
    private Boolean setoff;
    private Discipline discipline;
    private Specialty specialty;

    public Curriculum(){}

    public Curriculum(Long id) {
        this.id = id;
    }


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

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
