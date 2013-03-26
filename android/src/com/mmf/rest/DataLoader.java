package com.mmf.rest;

import com.mmf.prefs.OptionPrefs;
import com.mmf.prefs.SettingsPrefs;
import com.mmf.rest.exceptions.RestException;
import com.mmf.service.BusinessLayerException;
import com.mmf.util.EntityRegistry;
import com.mmf.db.DaoLayerException;
import com.mmf.db.dao.impl.DepartmentDao;
import com.mmf.db.dao.impl.LecturerDao;
import com.mmf.db.dao.impl.ScheduleDao;
import com.mmf.db.dao.impl.SpecialtyDao;
import com.mmf.db.model.*;
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
    
    public void loadSchedule(int course, int group, String subGroup, long idFilter) throws ServiceLayerException, InvalidCredentialsException {
        try {
            List<Schedule> lessons = RestRequester.gesSchedule(course, group, subGroup);
            for(Schedule schedule : lessons){
                schedule.setCourse(course);
                schedule.setGroupNumber(group);
                schedule.setSubGroup(subGroup);
                schedule.setFilterId(idFilter);
            }
            ScheduleDao dao = (ScheduleDao) EntityRegistry.get().getEntityDao(Schedule.class);
            dao.saveData(lessons);
        } catch (DaoLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    public void init() throws ServiceLayerException, InvalidCredentialsException {
        try {
            InitialData initialData = RestRequester.getInitialData();
            OptionPrefs.CourseAmount.put(initialData.getCourseAmount());
            OptionPrefs.GroupAmount.put(initialData.getGroupAmount());
            OptionPrefs.SubgroupAmount.put(getSubGroups(initialData.getSubGroups()));

            SettingsPrefs.FirstSemesterStart.put(initialData.getFirstSemesterStart());
            SettingsPrefs.FirstSemesterEnd.put(initialData.getFirstSemesterEnd());
            SettingsPrefs.SecondSemesterStart.put(initialData.getSecondSemesterStart());
            SettingsPrefs.SecondSemesterEnd.put(initialData.getSecondSemesterEnd());

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
        } catch (RestException e){
            throw new ServiceLayerException(e);
        }
    }

    private String getSubGroups(List<String> subGroups) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : subGroups){
            if (stringBuilder.length() != 0){
                stringBuilder.append(",");
            }
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
}
