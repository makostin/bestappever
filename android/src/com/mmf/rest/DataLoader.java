package com.mmf.rest;

import com.mmf.EntityRegistry;
import com.mmf.db.dao.impl.ScheduleDao;
import com.mmf.db.model.Schedule;
import com.mmf.rest.transport.RestRequester;

import java.util.List;

/**
 * User: admin
 * Date: 23.02.13
 */
public class DataLoader {

    private static DataLoader instance;

    public static synchronized DataLoader getInstance() {
        if (instance == null) {
            instance = new DataLoader();
        }
        return instance;
    }
    
    public void loadSchedule(String course, String group, String subGroup){
        List<Schedule> lessons = RestRequester.gesSchedule(course, group, subGroup);
        ScheduleDao dao = (ScheduleDao) EntityRegistry.get().getEntityDao(Schedule.class);
        dao.saveData(lessons);
    }
}
