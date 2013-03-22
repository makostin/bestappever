package com.mmf.business.domain;

import com.mmf.business.domain.adapter.DateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * svetlana.voyteh
 * 05.02.13
 */
public class DisciplineTime implements DomainClass<Long>{
    private static final long serialVersionUID = 2799886693021576931L;

    private Long id;
    private Date startTime;
    private Date endTime;
    private Integer number;
    private Integer breakTime;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
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
