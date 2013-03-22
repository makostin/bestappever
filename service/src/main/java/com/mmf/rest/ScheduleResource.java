package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.DisciplineService;
import com.mmf.business.LecturerService;
import com.mmf.business.ScheduleService;
import com.mmf.rest.response.ScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    public Response getSchedule(@QueryParam("course") int course, @QueryParam("group") int group, @QueryParam("subGroup") String subGroup){
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
            List<ScheduleResponse> scheduleList = scheduleService.getSchedule(semester, yearOfEntrance, String.valueOf(group), subGroupName);
            for(ScheduleResponse response : scheduleList){
                response.setLecturer(lecturerService.get(response.getLecturer().getId()));
                response.setDiscipline(disciplineService.get(response.getDiscipline().getId()));
            }
            return Response.ok(scheduleList).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }
}
