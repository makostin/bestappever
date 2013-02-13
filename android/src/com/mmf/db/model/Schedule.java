package com.mmf.db.model;

import java.util.Date;

/**
 * svetlana.voyteh
 * 13.02.13
 */
public class Schedule implements Entity{
    private static final long serialVersionUID = -6483758709565428143L;

    private Long id;
    private String time;
    private int number;
    private String supGroup;
    private int groupNumber;
    private int course;
    private int day;
    private int classroom;
    private String discipline;
    private Long date;
    private Long lecturerId;
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id; 
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSupGroup() {
        return supGroup;
    }

    public void setSupGroup(String supGroup) {
        this.supGroup = supGroup;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getClassroom() {
        return classroom;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
