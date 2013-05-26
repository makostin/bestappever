package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.LecturerService;
import com.mmf.business.ScheduleService;
import com.mmf.business.domain.Schedule;
import com.mmf.rest.response.schedule.ScheduleGroupResponse;
import com.mmf.rest.response.schedule.ScheduleResponse;
import com.mmf.rest.util.DomainUtil;
import com.mmf.rest.util.NullPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 19.03.13
 */
@Service
@Path("schedule")
public class ScheduleResource extends CrudResource<Schedule, ScheduleService>{

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private LecturerService lecturerService;

    @Override
    protected ScheduleService getService() {
        return scheduleService;
    }

    @Override
    protected void validate(Schedule domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getWeek());
            DomainUtil.checkingForNotNull(domain.getDayOfWeek());
            DomainUtil.checkingForNotNull(domain.getStudyId());
            DomainUtil.checkingForNotNull(domain.getClassroomId());
            DomainUtil.checkingForNotNull(domain.getDisciplineTimeId());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }
    }

    @Override
    protected void updateFields(Schedule domain, Schedule newDomain) {
        domain.setDayOfWeek(newDomain.getDayOfWeek());
        domain.setWeek(newDomain.getWeek());
        domain.setClassroomId(newDomain.getClassroomId());
        domain.setDisciplineTimeId(newDomain.getDisciplineTimeId());
        domain.setStudyId(newDomain.getStudyId());
    }

    @Override
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id){
        try {
            Schedule domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(new ScheduleResponse(domain)).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

    @Override
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        try {
            List<ScheduleResponse> scheduleResponses = new LinkedList<ScheduleResponse>();
            for(Schedule schedule : getService().list()){
                scheduleResponses.add(new ScheduleResponse(schedule));
            }
            return Response.ok(scheduleResponses).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/{id}/classroom")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClassroom(@PathParam("id") long id){
        try {
            Schedule domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getClassroom()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/{id}/study")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudy(@PathParam("id") long id){
        try {
            Schedule domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getStudy()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/{id}/disciplineTime")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisciplineTime(@PathParam("id") long id){
        try {
            Schedule domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getDisciplineTime()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/{id}/notes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotes(@PathParam("id") long id){
        try {
            Schedule domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getNotes()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/{id}/group")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroup(@PathParam("id") long id){
        try {
            Schedule domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getGroup()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/{id}/lecturer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLecturer(@PathParam("id") long id){
        try {
            Schedule domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getLecturer()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/{id}/discipline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscipline(@PathParam("id") long id){
        try {
            Schedule domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getDiscipline()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }


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
        String subGroupName = "".equals(subGroup) ? subGroup : String.valueOf(group) + subGroup;
        try {
            List<Schedule> scheduleList = scheduleService.getSchedule(semester, yearOfEntrance, String.valueOf(group), subGroupName);
            List<ScheduleResponse> scheduleResponseList = new LinkedList<ScheduleResponse>();
            ScheduleGroupResponse groupResponse = new ScheduleGroupResponse();
            int currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)%2;
            groupResponse.setCurrentWeek(currentWeek == 0 ? 2 : currentWeek);
            int day = 2;
            for (Schedule response : scheduleList) {
                if (day != response.getDayOfWeek()){
                    groupResponse.setSchedule(scheduleResponseList, day);
                    scheduleResponseList.clear();
                    day = response.getDayOfWeek();
                }
                response.setLecturer(lecturerService.get(response.getLecturerId()));
                scheduleResponseList.add(new ScheduleResponse(response));
            }
            groupResponse.setSchedule(scheduleResponseList, day);

            return Response.ok(groupResponse).header("Content-Encoding", "utf-8").build();
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
            List<ScheduleResponse> scheduleResponseList = new LinkedList<ScheduleResponse>();
            ScheduleGroupResponse groupResponse = new ScheduleGroupResponse();
            int currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)%2;
            groupResponse.setCurrentWeek(currentWeek == 0 ? 2 : currentWeek);
            int day = 2;
            for (Schedule response : scheduleList) {
                if (day != response.getDayOfWeek()){
                    groupResponse.setSchedule(scheduleResponseList, day);
                    scheduleResponseList.clear();
                    day = response.getDayOfWeek();
                }
                response.setLecturer(lecturerService.get(response.getLecturerId()));
                scheduleResponseList.add(new ScheduleResponse(response));
            }
            groupResponse.setSchedule(scheduleResponseList, day);

            return Response.ok(groupResponse).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }


}
