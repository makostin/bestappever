package com.mmf.service;

import com.mmf.db.dao.impl.LecturerDao;
import com.mmf.db.model.Filter;
import com.mmf.db.model.Lecturer;
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
    private final LecturerDao lecturerDao = (LecturerDao) EntityRegistry.get().getEntityDao(Lecturer.class);


    public List<Schedule> getLessonsForDay(Filter filter, int currentDay) {
        int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)%2;
        if (week == 0){
            week = 2;
        }

        List<Schedule> scheduleList = scheduleDao.getLessonsForDay(filter.getId(), currentDay, week);
        for (Schedule schedule : scheduleList){
            long lecturerId = schedule.getLecturer().getId();
            schedule.setLecturer(lecturerDao.get(lecturerId));
        }
        return scheduleList;
    }


}
