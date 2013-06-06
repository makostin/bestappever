package com.mmf.rest;

import com.mmf.db.dao.impl.*;
import com.mmf.prefs.OptionPrefs;
import com.mmf.prefs.SettingsPrefs;
import com.mmf.rest.exceptions.RestException;
import com.mmf.service.BusinessLayerException;
import com.mmf.util.EntityRegistry;
import com.mmf.db.DaoLayerException;
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
    
    public Filter loadSchedule(int course, int group, String subGroup) throws ServiceLayerException, InvalidCredentialsException {
        try {
            List<Schedule> lessons = RestRequester.gesSchedule(course, group, subGroup);
            ScheduleDao scheduleDao = (ScheduleDao) EntityRegistry.get().getEntityDao(Schedule.class);
            FilterDao filterDao = (FilterDao) EntityRegistry.get().getEntityDao(Filter.class);

            Filter filter = filterDao.getFilter(course, group, subGroup);
            if (filter != null){
                filterDao.delete(filter.getId());
                scheduleDao.deleteScheduleByFilter(filter.getId());
            }
            filter = new Filter();
            filter.setCourse(course);
            filter.setGroupNumber(group);
            filter.setSubGroup(subGroup);
            long idFilter = filterDao.saveData(filter);
            filter.setId(idFilter);

            for(Schedule schedule : lessons){
//                schedule.setCourse(course);
//                schedule.setGroupNumber(group);
//                schedule.setSubGroup(subGroup);
                schedule.setFilterId(idFilter);
            }
            scheduleDao.saveData(lessons);
            return filter;
        } catch (DaoLayerException e) {
            throw new ServiceLayerException(e);
        }
    }


    public Filter loadSchedule(Lecturer lecturer) throws InvalidCredentialsException, ServiceLayerException {
        try {
            List<Schedule> lessons = RestRequester.gesSchedule(lecturer.getId());
            ScheduleDao scheduleDao = (ScheduleDao) EntityRegistry.get().getEntityDao(Schedule.class);
            FilterDao filterDao = (FilterDao) EntityRegistry.get().getEntityDao(Filter.class);

            Filter filter = filterDao.getFilter(lecturer.getId());
            if (filter != null){
                filterDao.delete(filter.getId());
                scheduleDao.deleteScheduleByFilter(filter.getId());
            }
            filter = new Filter();
            filter.setLecturerId(lecturer.getId());
            long idFilter = filterDao.saveData(filter);
            filter.setId(idFilter);

            for(Schedule schedule : lessons){
//                schedule.setLecturer(lecturer);
                schedule.setFilterId(idFilter);
            }
            scheduleDao.saveData(lessons);
            return filter;
        } catch (DaoLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    public boolean login(String login, String password) throws InvalidCredentialsException, RestException {
        return RestRequester.login(login, password);
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
