package com.mmf.rest.response.schedule;

import com.mmf.business.domain.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * User: svetlana.voyteh
 * Date: 25.05.13
 */
@XmlRootElement
public class ScheduleResponse {

    private Long id;
    private Classroom classroom;
    private DisciplineTime disciplineTime;
    private Study study;
    private Integer dayOfWeek;
    private String dayTitle;
    private Integer week;
    private List<Note> notes = new LinkedList<Note>();


    public ScheduleResponse() {
    }

    public ScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.classroom = schedule.getClassroom();
        this.disciplineTime = schedule.getDisciplineTime();
        this.study = schedule.getStudy();
        this.week = schedule.getWeek();
        this.notes.addAll(schedule.getNotes());
        this.dayOfWeek = schedule.getDayOfWeek();
        setDay();
    }

    private void setDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        String dayTitle = new SimpleDateFormat("EEEE", new Locale("ru", "RU")).format(calendar.getTime());
        this.dayTitle = dayTitle.substring(0, 1).toUpperCase() + dayTitle.substring(1);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
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

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
