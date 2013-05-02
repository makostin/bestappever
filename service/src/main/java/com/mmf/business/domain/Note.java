package com.mmf.business.domain;


import java.util.Date;

/**
 * svetlana.voyteh
 * 05.02.13
 */
public class Note implements DomainClass<Long>{
    private static final long serialVersionUID = 8119639825256443031L;

    private Long id;
    private Date date;
    private User user;
    private Schedule schedule;
    private String color;
    private String text;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
