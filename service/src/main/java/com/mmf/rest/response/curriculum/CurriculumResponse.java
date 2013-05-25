package com.mmf.rest.response.curriculum;

import com.mmf.business.domain.Curriculum;
import com.mmf.business.domain.Discipline;
import com.mmf.business.domain.Specialty;

/**
 * User: svetlana.voyteh
 * Date: 25.05.13
 */
public class CurriculumResponse {

    private Long id;
    private Integer hours;
    private Integer semester;
    private Boolean exam;
    private Boolean setoff;
    private Discipline discipline;
    private Specialty specialty;

    public CurriculumResponse() {
    }

    public CurriculumResponse(Curriculum curriculum) {
        this.id = curriculum.getId();
        this.hours = curriculum.getHours();
        this.semester = curriculum.getSemester();
        this.exam = curriculum.getExam();
        this.setoff = curriculum.getSetoff();
        this.discipline = curriculum.getDiscipline();
        this.specialty = curriculum.getSpecialty();
    }


    public Long getId() {
        return id;
    }

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
