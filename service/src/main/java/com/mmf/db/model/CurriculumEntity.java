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
    private Integer hoursOfWeek;
    private Integer groupNumber;
    private Integer semester;
    private Integer yearOfEntrance;
    private Boolean exam;
    private Boolean setoff;
    private DisciplineEntity discipline;


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

    public Integer getHoursOfWeek() {
        return hoursOfWeek;
    }

    public void setHoursOfWeek(Integer hoursOfWeek) {
        this.hoursOfWeek = hoursOfWeek;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getYearOfEntrance() {
        return yearOfEntrance;
    }

    public void setYearOfEntrance(Integer yearOfEntrance) {
        this.yearOfEntrance = yearOfEntrance;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Discipline")
    public DisciplineEntity getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineEntity discipline) {
        this.discipline = discipline;
    }
}
