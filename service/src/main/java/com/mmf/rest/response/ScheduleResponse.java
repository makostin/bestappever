package com.mmf.rest.response;

import java.util.ArrayList;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 19.03.13
 */
public class ScheduleResponse {
    
    private List<DisciplineResponse> disciplines = new ArrayList<DisciplineResponse>();
    private int day;
    private String dayTitle;


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<DisciplineResponse> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<DisciplineResponse> disciplines) {
        this.disciplines = disciplines;
    }

    public String getDayTitle() {
        return dayTitle;
    }

    public void setDayTitle(String dayTitle) {
        this.dayTitle = dayTitle;
    }
}
