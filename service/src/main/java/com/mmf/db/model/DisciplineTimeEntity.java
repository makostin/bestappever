package com.mmf.db.model;

import javax.persistence.*;
import java.util.Date;

/**
 * svetlana.voyteh
 * 05.02.13
 */
@Entity
@Table(name = "disciplineTime")
public class DisciplineTimeEntity implements EntityClass<Long>{
    private static final long serialVersionUID = -6310640684203045277L;
    private Long id;
    private Date startTime;
    private Date endTime;
    private Integer number;
    private Integer breakTime;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Integer breakTime) {
        this.breakTime = breakTime;
    }
}
