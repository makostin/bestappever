package com.mmf.business.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;

/**
 * svetlana.voyteh
 * 05.02.13
 */
@XmlRootElement
public class Schedule implements DomainClass<Long>{
    private static final long serialVersionUID = -8843236541522339438L;

    private Long id;
    private Classroom classroom;
    private DisciplineTime disciplineTime;
    private Study study;
    private Group group;
    private Lecturer lecturer;
    private Discipline discipline;
    private Integer dayOfWeek;
    private String dayTitle;
    private Integer everyNWeek;
    private Integer week;
    private Set<Note> notes = new HashSet<Note>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDayTitle() {
        return dayTitle;
    }

    public void setDayTitle(String dayTitle) {
        this.dayTitle = dayTitle;
    }

    @XmlTransient
    public Integer getEveryNWeek() {
        return everyNWeek;
    }

    public void setEveryNWeek(Integer everyNWeek) {
        this.everyNWeek = everyNWeek;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public DisciplineTime getDisciplineTime() {
        return disciplineTime;
    }

    public void setDisciplineTime(DisciplineTime disciplineTime) {
        this.disciplineTime = disciplineTime;
    }

    @XmlTransient
    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
