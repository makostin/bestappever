package com.mmf.service;

import com.mmf.util.EntityRegistry;
import com.mmf.db.dao.impl.ScheduleDao;
import com.mmf.db.model.Schedule;

import java.util.Calendar;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 2/3/12
 */
public class ScheduleService {

    private final ScheduleDao scheduleDao = (ScheduleDao) EntityRegistry.get().getEntityDao(Schedule.class);


    public List<Schedule> getLessonsForDay(int course, int group, String subGroup, int currentDay) {
        int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)%2;
        if (week == 0){
            week = 2;
        }
        return scheduleDao.getLessonsForDay(course, group, subGroup, currentDay, week);
    }
}
