package com.mmf.rest.response;

import com.mmf.business.domain.Schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 19.03.13
 */
public class ScheduleResponse extends DataResponse<Schedule>{
    
    private int currentWeek;

    public ScheduleResponse(List<Schedule> scheduleList) {
        super(scheduleList);
        currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)%2 == 0 ? 2 : 1;
    }

    public ScheduleResponse() {
        currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)%2 == 0 ? 2 : 1;
    }


    public int getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }
}
