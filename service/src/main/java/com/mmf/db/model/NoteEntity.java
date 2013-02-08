package com.mmf.db.model;

import javax.persistence.*;
import java.util.Date;

/**
 * svetlana.voyteh
 * 05.02.13
 */
@Entity
@Table(name = "note")
public class NoteEntity implements EntityClass<Long>{
    private static final long serialVersionUID = 6519178846878752336L;
    private Long id;
    private Date date;
    private UserEntity user;
    private ScheduleEntity schedule;
    private String color;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "IdUser")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "IdSchedule")
    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
