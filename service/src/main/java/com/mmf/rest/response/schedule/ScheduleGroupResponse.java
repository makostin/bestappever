package com.mmf.rest.response.schedule;

import java.util.LinkedList;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 26.05.13
 */
public class ScheduleGroupResponse {

    private Integer currentWeek;
    private List<ScheduleResponse> monday = new LinkedList<ScheduleResponse>();
    private List<ScheduleResponse> tuesday = new LinkedList<ScheduleResponse>();
    private List<ScheduleResponse> wednesday = new LinkedList<ScheduleResponse>();
    private List<ScheduleResponse> thursday = new LinkedList<ScheduleResponse>();
    private List<ScheduleResponse> friday = new LinkedList<ScheduleResponse>();
    private List<ScheduleResponse> saturday = new LinkedList<ScheduleResponse>();

    public void setSchedule(List<ScheduleResponse> schedule, int day){
        switch (day){
            case 2:
                monday.addAll(schedule);
                break;
            case 3:
                tuesday.addAll(schedule);
                break;
            case 4:
                wednesday.addAll(schedule);
                break;
            case 5:
                thursday.addAll(schedule);
                break;
            case 6:
                friday.addAll(schedule);
                break;
            case 7:
                saturday.addAll(schedule);
                break;
        }
    }

    public Integer getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(Integer currentWeek) {
        this.currentWeek = currentWeek;
    }

    public List<ScheduleResponse> getMonday() {
        return monday;
    }

    public void setMonday(List<ScheduleResponse> monday) {
        this.monday = monday;
    }

    public List<ScheduleResponse> getTuesday() {
        return tuesday;
    }

    public void setTuesday(List<ScheduleResponse> tuesday) {
        this.tuesday = tuesday;
    }

    public List<ScheduleResponse> getWednesday() {
        return wednesday;
    }

    public void setWednesday(List<ScheduleResponse> wednesday) {
        this.wednesday = wednesday;
    }

    public List<ScheduleResponse> getThursday() {
        return thursday;
    }

    public void setThursday(List<ScheduleResponse> thursday) {
        this.thursday = thursday;
    }

    public List<ScheduleResponse> getFriday() {
        return friday;
    }

    public void setFriday(List<ScheduleResponse> friday) {
        this.friday = friday;
    }

    public List<ScheduleResponse> getSaturday() {
        return saturday;
    }

    public void setSaturday(List<ScheduleResponse> saturday) {
        this.saturday = saturday;
    }
}
