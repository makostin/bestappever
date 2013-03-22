package com.mmf.rest.response;

import com.mmf.business.domain.Classroom;
import com.mmf.business.domain.Discipline;
import com.mmf.business.domain.DisciplineTime;
import com.mmf.business.domain.Lecturer;

/**
 * User: svetlana.voyteh
 * Date: 19.03.13
 */
public class ScheduleResponse {
    
    private Classroom classroom;
    private Lecturer lecturer;
    private Discipline discipline;
    private DisciplineTime time;
    private int dayOfWeek;
    private int week;
    private int everyNWeek;

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
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

    public DisciplineTime getTime() {
        return time;
    }

    public void setTime(DisciplineTime time) {
        this.time = time;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getEveryNWeek() {
        return everyNWeek;
    }

    public void setEveryNWeek(int everyNWeek) {
        this.everyNWeek = everyNWeek;
    }
}
