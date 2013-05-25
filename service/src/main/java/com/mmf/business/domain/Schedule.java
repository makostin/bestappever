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
    private Long classroomId;
    private DisciplineTime disciplineTime;
    private Long disciplineTimeId;
    private Study study;
    private Long studyId;
    private Group group;
    private Long groupId;
    private Lecturer lecturer;
    private Long lecturerId;
    private Discipline discipline;
    private Long disciplineId;
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

    @XmlTransient
    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    @XmlTransient
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

    @XmlTransient
    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    @XmlTransient
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @XmlTransient
    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @XmlTransient
    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Long getDisciplineTimeId() {
        return disciplineTimeId;
    }

    public void setDisciplineTimeId(Long disciplineTimeId) {
        this.disciplineTimeId = disciplineTimeId;
    }

    public Long getStudyId() {
        return studyId;
    }

    public void setStudyId(Long studyId) {
        this.studyId = studyId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Long disciplineId) {
        this.disciplineId = disciplineId;
    }

    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }
}
