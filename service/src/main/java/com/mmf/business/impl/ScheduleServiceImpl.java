package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.ScheduleService;
import com.mmf.business.domain.Schedule;
import com.mmf.business.domain.utils.*;
import com.mmf.db.dao.ScheduleDao;
import com.mmf.db.model.ScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * User: svetlana.voyteh
 * Date: 19.03.13
 */
@Named
public class ScheduleServiceImpl extends AbstractCrudService<Long, Schedule, ScheduleEntity, ScheduleDao> implements ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    protected ScheduleDao getDao() {
        return scheduleDao;
    }

    @Override
    public void convertToEntity(Schedule domain, ScheduleEntity entity) throws BusinessServiceException {
        if (domain != null) {
            // todo
        }
    }

    @Override
    public Schedule convertToDomain(ScheduleEntity entity) throws BusinessServiceException {
        if (entity == null) {
            return null;
        }

        Schedule schedule = ScheduleHelper.convertToDomain(entity);
        schedule.setClassroom(ClassroomHelper.convertToDomain(entity.getClassroom()));
        schedule.setDisciplineTime(DisciplineTimeHelper.convertToDomain(entity.getDisciplineTime()));
        schedule.setStudy(StudyHelper.convertToDomain(entity.getStudy()));
        // todo: add notes
        return schedule;
    }

    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public List<Schedule> getSchedule(int semester, int yearOfEntrance, String groupName, String subGroupName) throws BusinessServiceException {
        List<Schedule> responseList = new ArrayList<Schedule>();
        List<ScheduleEntity> scheduleList = new ArrayList<ScheduleEntity>();
        for (int i = 2; i <= 7; i++) {
            scheduleList.addAll(scheduleDao.getScheduleForDay(semester, yearOfEntrance, groupName, subGroupName, i));
        }

        if (scheduleList.isEmpty()) {
            return responseList;
        }

        for (ScheduleEntity entity : scheduleList) {
            Schedule schedule = convertToDomain(entity);
            schedule.setLecturer(schedule.getStudy().getLecturer());
            schedule.setDiscipline(DisciplineHelper.convertToDomain(entity.getStudy().getCurriculum().getDiscipline()));
            schedule.setGroup(schedule.getStudy().getGroup());
            setDay(schedule);
            responseList.add(schedule);
        }
        return responseList;
    }

    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public List<Schedule> getSchedule(long lecturerId, int semester) throws BusinessServiceException {
        List<Schedule> responseList = new ArrayList<Schedule>();
        List<ScheduleEntity> scheduleList = new ArrayList<ScheduleEntity>();
        for (int i = 2; i <= 7; i++) {
            scheduleList.addAll(scheduleDao.getScheduleForDay(semester, lecturerId, i));
        }

        if (scheduleList.isEmpty()) {
            return responseList;
        }

        for (ScheduleEntity entity : scheduleList) {
            Schedule schedule = convertToDomain(entity);
            schedule.setDiscipline(DisciplineHelper.convertToDomain(entity.getStudy().getCurriculum().getDiscipline()));
            schedule.setGroup(GroupHelper.convertToDomain(entity.getStudy().getGroup()));
            schedule.setLecturer(schedule.getStudy().getLecturer());
            setDay(schedule);

            responseList.add(schedule);
        }
        return responseList;
    }

    private void setDay(Schedule response) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, response.getDayOfWeek());
        String dayTitle = new SimpleDateFormat("EEEE", new Locale("ru", "RU")).format(calendar.getTime());

        response.setDayTitle(dayTitle.substring(0, 1).toUpperCase() + dayTitle.substring(1));
    }

}
