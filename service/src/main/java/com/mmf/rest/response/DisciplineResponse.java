package com.mmf.rest.response;

import com.mmf.business.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * svetlana.voyteh
 * 15.04.13
 */
public class DisciplineResponse {

    private Lecturer lecturer;
    private String classroom;
    private String name;
    private DisciplineTime time;
    private List<Note> notes = new ArrayList<Note>();
    private int week;


    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public DisciplineTime getTime() {
        return time;
    }

    public void setTime(DisciplineTime time) {
        this.time = time;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
