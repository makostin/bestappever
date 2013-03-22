package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.ScheduleService;
import com.mmf.business.domain.Curriculum;
import com.mmf.business.domain.Schedule;
import com.mmf.business.domain.utils.*;
import com.mmf.db.dao.ScheduleDao;
import com.mmf.db.model.ScheduleEntity;
import com.mmf.rest.response.ScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 19.03.13
 */
@Named
public class ScheduleServiceImpl extends AbstractCrudService<Long, Schedule, ScheduleEntity, ScheduleDao> implements ScheduleService{

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    protected ScheduleDao getDao() {
        return scheduleDao;
    }

    @Override
    public void convertToEntity(Schedule domain, ScheduleEntity entity) throws BusinessServiceException {
        if (domain != null){
            // todo
        }
    }

    @Override
    public Schedule convertToDomain(ScheduleEntity entity) throws BusinessServiceException {
        if(entity == null){
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
    public List<ScheduleResponse> getSchedule(int semester, int yearOfEntrance, String groupName, String subGroupName) throws BusinessServiceException {
        List<ScheduleEntity> scheduleList = scheduleDao.getSchedule(semester, yearOfEntrance, groupName, subGroupName);
        List<ScheduleResponse> responseList = new ArrayList<ScheduleResponse>();
        for(ScheduleEntity entity : scheduleList){
            ScheduleResponse scheduleResponse = new ScheduleResponse();
            Schedule schedule = convertToDomain(entity);
            Curriculum curriculum = CurriculumHelper.convertToDomain(entity.getStudy().getCurriculum());
            scheduleResponse.setDiscipline(curriculum.getDiscipline());
            scheduleResponse.setLecturer(schedule.getStudy().getLecturer());
            scheduleResponse.setWeek(schedule.getWeek());
            scheduleResponse.setClassroom(schedule.getClassroom());
            scheduleResponse.setTime(schedule.getDisciplineTime());
            scheduleResponse.setDayOfWeek(schedule.getDayOfWeek());
            responseList.add(scheduleResponse);
        }
        return responseList;  
    }
}
