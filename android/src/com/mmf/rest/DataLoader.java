package com.mmf.rest;

import com.mmf.EntityRegistry;
import com.mmf.db.DaoLayerException;
import com.mmf.db.dao.impl.DepartmentDao;
import com.mmf.db.dao.impl.LecturerDao;
import com.mmf.db.dao.impl.ScheduleDao;
import com.mmf.db.dao.impl.SpecialtyDao;
import com.mmf.db.model.*;
import com.mmf.rest.transport.RestRequester;
import com.mmf.rest.exceptions.ServiceLayerException;

import java.util.List;

/**
 * User: svetlana.voyteh
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
    
    public void loadSchedule(String course, String group, String subGroup) throws ServiceLayerException {
        try {
            List<Schedule> lessons = RestRequester.gesSchedule(course, group, subGroup);
            ScheduleDao dao = (ScheduleDao) EntityRegistry.get().getEntityDao(Schedule.class);
            dao.saveData(lessons);
        } catch (DaoLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    public void init() throws ServiceLayerException  {
        try {
            List<Specialty> specialties = RestRequester.getSpecialities();
            List<Department> departments = RestRequester.getDepartments();
            List<Lecturer> lecturers = RestRequester.getLecturers();

            SpecialtyDao specialtyDao = (SpecialtyDao) EntityRegistry.get().getEntityDao(Specialty.class);
            specialtyDao.saveData(specialties);

            DepartmentDao departmentDao = (DepartmentDao) EntityRegistry.get().getEntityDao(Department.class);
            departmentDao.saveData(departments);

            LecturerDao lecturerDao = (LecturerDao) EntityRegistry.get().getEntityDao(Lecturer.class);
            lecturerDao.saveLecturerList(lecturers);
        } catch (DaoLayerException e) {
            throw new ServiceLayerException(e);
        }
    }
}
