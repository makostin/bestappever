package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.DisciplineService;
import com.mmf.business.LecturerService;
import com.mmf.business.ScheduleService;
import com.mmf.business.domain.Schedule;
import com.mmf.rest.response.DisciplineResponse;
import com.mmf.rest.response.ResponseDetails;
import com.mmf.rest.response.ScheduleResponse;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 19.03.13
 */
@Service
@Path("schedule")
public class ScheduleResource {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private DisciplineService disciplineService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSchedule(@QueryParam("course") int course, @QueryParam("group") int group, @QueryParam("subGroup") @DefaultValue("") String subGroup, @QueryParam("lecturerId") Long lecturerId){
        if (lecturerId == null && (course == 0  || group == 0)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (lecturerId != null && (course != 0  || group != 0)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (lecturerId == null){
            return  getScheduleForStudent(course, group, subGroup);
        } else {
            return getScheduleForLecturer(lecturerId);
        }
    }

    private Response getScheduleForStudent(int course, int group, String subGroup){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int semester;
        int yearOfEntrance;
        if (currentMonth < Calendar.JULY){
            semester = course*2;
            yearOfEntrance = currentYear - course;
        } else {
            semester = course*2 - 1;
            yearOfEntrance = currentYear - course +1;
        }
        String subGroupName = String.valueOf(group) + subGroup;
        try {
            List<Schedule> scheduleList = scheduleService.getSchedule(semester, yearOfEntrance, String.valueOf(group), subGroupName);
            for(Schedule response : scheduleList){
                response.setLecturer(lecturerService.get(response.getLecturer().getId()));
                response.getGroup().setCourse(course);
                response.getGroup().setNumber(group);
                response.getGroup().setSubgroup(subGroup);
//                for(DisciplineResponse disciplineResponse : response.getDisciplines()){
//                    disciplineResponse.setLecturer(lecturerService.get(disciplineResponse.getLecturer().getId()));
//                    disciplineResponse.setCourse(course);
//                    disciplineResponse.setGroup(group);
//                    disciplineResponse.setSubGroup(subGroup);
//                }
            }

            ResponseDetails response = new ResponseDetails();
            response.setCode(Response.Status.OK.getStatusCode());
            response.setData(new ScheduleResponse(scheduleList));
            return Response.ok(response).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    private Response getScheduleForLecturer(long lecturerId){
        try {
            int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
            int semester;
            if (currentMonth < Calendar.JULY){
                semester = 0;
            } else {
                semester = 1;
            }
            List<Schedule> scheduleList = scheduleService.getSchedule(lecturerId, semester);
            for(Schedule response : scheduleList){
                response.setLecturer(lecturerService.get(response.getLecturer().getId()));
//                for(DisciplineResponse disciplineResponse : response.getDisciplines()){
//                    disciplineResponse.setLecturer(lecturerService.get(disciplineResponse.getLecturer().getId()));
//                }
            }
            ResponseDetails response = new ResponseDetails();
            response.setCode(Response.Status.OK.getStatusCode());
            response.setData(new ScheduleResponse(scheduleList));
            return Response.ok(response).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }
}
