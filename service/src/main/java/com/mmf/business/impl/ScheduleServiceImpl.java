package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.ScheduleService;
import com.mmf.business.domain.Schedule;
import com.mmf.business.domain.utils.*;
import com.mmf.db.dao.ScheduleDao;
import com.mmf.db.model.GroupEntity;
import com.mmf.db.model.ScheduleEntity;
import com.mmf.rest.response.DisciplineResponse;
import com.mmf.rest.response.ScheduleResponse;
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
    public List<Schedule> getSchedule(int semester, int yearOfEntrance, String groupName, String subGroupName) throws BusinessServiceException {
        List<Schedule> responseList = new ArrayList<Schedule>();
        List<ScheduleEntity> scheduleList = new ArrayList<ScheduleEntity>();
        for (int i = 2; i <= 7; i++){
            scheduleList.addAll(scheduleDao.getScheduleForDay(semester, yearOfEntrance, groupName, subGroupName, i));
        }

        if(scheduleList.isEmpty()){
            return responseList;
        }

//        ScheduleResponse scheduleResponse = new ScheduleResponse();
//        int day = scheduleList.get(0).getDayOfWeek();
//        int size = scheduleList.size();
        for(ScheduleEntity entity : scheduleList){
//            size--;
            Schedule schedule = convertToDomain(entity);
            schedule.setLecturer(schedule.getStudy().getLecturer());
            schedule.setDiscipline(DisciplineHelper.convertToDomain(entity.getStudy().getCurriculum().getDiscipline()));
            schedule.setGroup(schedule.getStudy().getGroup());
            setDay(schedule);

//            DisciplineResponse disciplineResponse = new DisciplineResponse();
//            disciplineResponse.setName(entity.getStudy().getCurriculum().getDiscipline().getName());
//            disciplineResponse.setLecturer(schedule.getStudy().getLecturer());
//            disciplineResponse.setClassroom(schedule.getClassroom().getNumber());
//            disciplineResponse.setTime(schedule.getDisciplineTime());
//            disciplineResponse.setWeek(schedule.getWeek());
//
//            if (day != schedule.getDayOfWeek()) {
//                setDay(scheduleResponse, day);
//                responseList.add(scheduleResponse);
//                scheduleResponse = new ScheduleResponse();
//                scheduleResponse.getDisciplines().add(disciplineResponse);
//                day = schedule.getDayOfWeek();
//            } else if  (size == 0){
//                setDay(scheduleResponse, day);
//                scheduleResponse.getDisciplines().add(disciplineResponse);
//                responseList.add(scheduleResponse);
//            } else {
//                scheduleResponse.getDisciplines().add(disciplineResponse);
//            }
            responseList.add(schedule);
        }
        return responseList;  
    }

    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public List<Schedule> getSchedule(long lecturerId, int semester) throws BusinessServiceException {
        List<Schedule> responseList = new ArrayList<Schedule>();

//        List<ScheduleEntity> scheduleList = scheduleDao.getSchedule(semester, lecturerId);
        List<ScheduleEntity> scheduleList = new ArrayList<ScheduleEntity>();
        for (int i = 2; i <= 7; i++){
            scheduleList.addAll(scheduleDao.getScheduleForDay(semester, lecturerId, i));
        }

        if(scheduleList.isEmpty()){
            return responseList;
        }


//        ScheduleResponse scheduleResponse = new ScheduleResponse();
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for(ScheduleEntity entity : scheduleList){
//            size--;
            Schedule schedule = convertToDomain(entity);
            schedule.setDiscipline(DisciplineHelper.convertToDomain(entity.getStudy().getCurriculum().getDiscipline()));
            schedule.setGroup(GroupHelper.convertToDomain(entity.getStudy().getGroup()));
            schedule.setLecturer(schedule.getStudy().getLecturer());
            setDay(schedule);

            responseList.add(schedule);


//            DisciplineResponse disciplineResponse = new DisciplineResponse();
//            disciplineResponse.setName(entity.getStudy().getCurriculum().getDiscipline().getName());
//            GroupEntity groupEntity = entity.getStudy().getGroup();
//            if (groupEntity.getName().length() > 1){
//                disciplineResponse.setSubGroup(groupEntity.getName().substring(groupEntity.getName().length()-1));
//                disciplineResponse.setGroup(Integer.parseInt(groupEntity.getName().substring(0, groupEntity.getName().length()-1)));
//            } else {
//                disciplineResponse.setGroup(Integer.parseInt(groupEntity.getName()));
//            }
//
//            if (currentMonth < Calendar.JULY){
//                disciplineResponse.setCourse(currentYear - groupEntity.getYear());
//            } else {
//                disciplineResponse.setCourse(currentYear - groupEntity.getYear()+1);
//            }
//            disciplineResponse.setLecturer(schedule.getStudy().getLecturer());
//            disciplineResponse.setClassroom(schedule.getClassroom().getNumber());
//            disciplineResponse.setTime(schedule.getDisciplineTime());
//            disciplineResponse.setWeek(schedule.getWeek());

//            if (day != schedule.getDayOfWeek()) {
//                setDay(scheduleResponse, day);
//                responseList.add(scheduleResponse);
//                scheduleResponse = new ScheduleResponse();
//                scheduleResponse.getDisciplines().add(disciplineResponse);
//                day = schedule.getDayOfWeek();
//            } else if  (size == 0){
//                setDay(scheduleResponse, day);
//                scheduleResponse.getDisciplines().add(disciplineResponse);
//                responseList.add(scheduleResponse);
//            } else {
//                scheduleResponse.getDisciplines().add(disciplineResponse);
//            }
        }
        return responseList;
    }

    private void setDay(Schedule response){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, response.getDayOfWeek());
        String dayTitle = new SimpleDateFormat("EEEE", new Locale("ru", "RU")).format(calendar.getTime());

        response.setDayTitle(dayTitle.substring(0, 1).toUpperCase() + dayTitle.substring(1));
    }

}
