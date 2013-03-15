package com.mmf.rest;

import com.mmf.EntityRegistry;
import com.mmf.db.DaoLayerException;
import com.mmf.db.dao.impl.DepartmentDao;
import com.mmf.db.dao.impl.LecturerDao;
import com.mmf.db.dao.impl.ScheduleDao;
import com.mmf.db.dao.impl.SpecialtyDao;
import com.mmf.db.model.*;
import com.mmf.prefs.CredentialsPrefs;
import com.mmf.rest.domain.InitialData;
import com.mmf.rest.transport.RestRequester;
import com.mmf.rest.exceptions.ServiceLayerException;
import org.apache.http.auth.InvalidCredentialsException;

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

    public void init() throws ServiceLayerException, InvalidCredentialsException {
        try {
            InitialData initialData = RestRequester.getInitialData();

            List<Specialty> specialties = initialData.getSpecialties() ;
            List<Department> departments = initialData.getDepartments() ;
            List<Lecturer> lecturers = initialData.getLecturers() ;

            SpecialtyDao specialtyDao = (SpecialtyDao) EntityRegistry.get().getEntityDao(Specialty.class);
            specialtyDao.saveData(specialties);

            DepartmentDao departmentDao = (DepartmentDao) EntityRegistry.get().getEntityDao(Department.class);
            departmentDao.saveData(departments);

            LecturerDao lecturerDao = (LecturerDao) EntityRegistry.get().getEntityDao(Lecturer.class);
            lecturerDao.saveData(lecturers);
        } catch (DaoLayerException e) {
            throw new ServiceLayerException(e);
        }
    }
}
