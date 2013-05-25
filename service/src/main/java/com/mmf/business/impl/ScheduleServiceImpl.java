package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.ScheduleService;
import com.mmf.business.domain.Schedule;
import com.mmf.business.domain.utils.*;
import com.mmf.db.dao.*;
import com.mmf.db.model.*;
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

    @Autowired
    private ClassroomDao classroomDao;

    @Autowired
    private DisciplineTimeDao disciplineTimeDao;

    @Autowired
    private StudyDao studyDao;

    @Override
    protected ScheduleDao getDao() {
        return scheduleDao;
    }

    @Override
    public void convertToEntity(Schedule domain, ScheduleEntity entity) throws BusinessServiceException {
        if (domain != null) {
            try {
                ScheduleHelper.convertToEntity(domain, entity);
                ClassroomEntity classroomEntity = classroomDao.getEntityInstance(domain.getClassroomId());
                if (classroomEntity == null){
                    throw new BusinessServiceException("Such classroom doesn't exist.");
                }

                if (entity != null){
                    entity.setClassroom(classroomEntity);
                }

                DisciplineTimeEntity disciplineTimeEntity = disciplineTimeDao.getEntityInstance(domain.getDisciplineTimeId());
                if (disciplineTimeEntity == null){
                    throw new BusinessServiceException("Such disciplineTime doesn't exist.");
                }

                if (entity != null){
                    entity.setDisciplineTime(disciplineTimeEntity);
                }

                StudyEntity studyEntity = studyDao.getEntityInstance(domain.getStudyId());
                if(studyEntity == null){
                    throw new BusinessServiceException("Such study doesn't exist.");
                }

                if (entity != null){
                    entity.setStudy(studyEntity);
                }
            } catch (DataAccessException e) {
                throw new BusinessServiceException("Conversion to group entity error.", e);
            }
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
        schedule.setGroup(GroupHelper.convertToDomain(entity.getStudy().getGroup()));
        schedule.setLecturer(LecturerHelper.convertToDomain(entity.getStudy().getLecturer()));
        schedule.setDiscipline(DisciplineHelper.convertToDomain(entity.getStudy().getCurriculum().getDiscipline()));
        for (NoteEntity noteEntity : entity.getNotes()){
            schedule.getNotes().add(NoteHelper.convertToDomain(noteEntity));
        }
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
