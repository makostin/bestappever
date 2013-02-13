package com.mmf.db.model;

/**
 * svetlana.voyteh
 * 13.02.13
 */
public class Note implements Entity{
    private static final long serialVersionUID = 3265414405185444570L;

    private Long id;
    private String data;
    private Long scheduleId;
   
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}
