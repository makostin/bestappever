package com.mmf.rest.response.note;

import com.mmf.business.domain.Note;
import com.mmf.business.domain.Schedule;
import com.mmf.business.domain.User;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * User: svetlana.voyteh
 * Date: 25.05.13
 */
@XmlRootElement
public class NoteResponse {

    private Long id;
    private Date date;
    private User user;
    private Schedule schedule;
    private String color;
    private String text;

    public NoteResponse() {
    }

    public NoteResponse(Note note) {
        this.id = note.getId();
        this.color = note.getColor();
        this.date = note.getDate();
        this.text = note.getText();
        this.user = note.getUser();
        this.schedule = note.getSchedule();
    }

    public Long getId() {
        return id;
    }

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
