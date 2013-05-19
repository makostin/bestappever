package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.LecturerService;
import com.mmf.business.ScheduleService;
import com.mmf.business.domain.Schedule;
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


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSchedule(@QueryParam("course") int course, @QueryParam("group") int group, @QueryParam("subGroup") @DefaultValue("") String subGroup, @QueryParam("lecturerId") Long lecturerId) {
        if (lecturerId == null && (course == 0 || group == 0)) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }

        if (lecturerId != null && (course != 0 || group != 0)) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }

        if (lecturerId == null) {
            return getScheduleForStudent(course, group, subGroup);
        } else {
            return getScheduleForLecturer(lecturerId);
        }
    }

    private Response getScheduleForStudent(int course, int group, String subGroup) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int semester;
        int yearOfEntrance;
        if (currentMonth < Calendar.JULY) {
            semester = course * 2;
            yearOfEntrance = currentYear - course;
        } else {
            semester = course * 2 - 1;
            yearOfEntrance = currentYear - course + 1;
        }
        String subGroupName = String.valueOf(group) + subGroup;
        try {
            List<Schedule> scheduleList = scheduleService.getSchedule(semester, yearOfEntrance, String.valueOf(group), subGroupName);
            for (Schedule response : scheduleList) {
                response.setLecturer(lecturerService.get(response.getLecturer().getId()));
                response.getGroup().setCourse(course);
                response.getGroup().setNumber(group);
                response.getGroup().setSubgroup(subGroup);
            }

            return Response.ok(scheduleList).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    private Response getScheduleForLecturer(long lecturerId) {
        try {
            int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
            int semester;
            if (currentMonth < Calendar.JULY) {
                semester = 0;
            } else {
                semester = 1;
            }
            List<Schedule> scheduleList = scheduleService.getSchedule(lecturerId, semester);
            for (Schedule response : scheduleList) {
                response.setLecturer(lecturerService.get(response.getLecturer().getId()));
            }

            return Response.ok(scheduleList).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }
}
