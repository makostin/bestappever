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
}
