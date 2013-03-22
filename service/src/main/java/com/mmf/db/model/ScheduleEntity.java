package com.mmf.db.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * svetlana.voyteh
 * 05.02.13
 */
@Entity
@Table(name = "schedule")
public class ScheduleEntity implements EntityClass<Long>{
    private static final long serialVersionUID = 133698310763260308L;
    private Long id;
    private ClassroomEntity classroom;
    private DisciplineTimeEntity disciplineTime;
    private StudyEntity study;
    private Integer dayOfWeek;
    private Integer everyNWeek;
    private Integer week;
    private Set<NoteEntity> notes = new HashSet<NoteEntity>();

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

    @ManyToOne
    @JoinColumn(name = "IdClassroom")
    public ClassroomEntity getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomEntity classroom) {
        this.classroom = classroom;
    }

    @ManyToOne
    @JoinColumn(name = "IdDisciplineTime")
    public DisciplineTimeEntity getDisciplineTime() {
        return disciplineTime;
    }

    public void setDisciplineTime(DisciplineTimeEntity disciplineTime) {
        this.disciplineTime = disciplineTime;
    }

    @ManyToOne
    @JoinColumn(name = "IdStudy")
    public StudyEntity getStudy() {
        return study;
    }

    public void setStudy(StudyEntity study) {
        this.study = study;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

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

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    public Set<NoteEntity> getNotes() {
        return notes;
    }

    public void setNotes(Set<NoteEntity> notes) {
        this.notes = notes;
    }
}
