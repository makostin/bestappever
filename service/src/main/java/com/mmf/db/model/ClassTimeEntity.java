package com.mmf.db.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "classtime")
public class ClassTimeEntity implements EntityClass<Long>{
    private static final long serialVersionUID = 1866622766091712725L;
    private Long id;
    private Date start;
    private Date end;
    private Integer breakTime;
    private Integer number;

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Integer breakTime) {
        this.breakTime = breakTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
