package com.mmf.service;

import com.mmf.util.EntityRegistry;
import com.mmf.db.dao.impl.ScheduleDao;
import com.mmf.db.model.Schedule;

import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 2/3/12
 */
public class ScheduleService {

    private final ScheduleDao scheduleDao = (ScheduleDao) EntityRegistry.get().getEntityDao(Schedule.class);

    public ScheduleService() {
    }


    public List<Schedule> getLessonsForDay(int course, int group, int currentDay) {
        //todo
        return null;
    }
}
