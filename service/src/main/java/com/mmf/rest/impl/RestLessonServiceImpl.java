package com.mmf.rest.impl;

import com.mmf.business.service.BusinessServiceException;
import com.mmf.business.service.LessonService;
import com.mmf.db.model.GroupEntity;
import com.mmf.db.model.UserEntity;
import com.mmf.rest.RestLessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 5/13/12
 */
@Path("/lesson")
public class RestLessonServiceImpl implements RestLessonService{

    private static final Logger LOG = LoggerFactory.getLogger(RestLessonServiceImpl.class);

    @Inject
    private LessonService lessonService;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/create")
    @Override
    public Response create(LessonEntity lesson) {
        try {
            lessonService.create(lesson);
            return Response.ok(lesson).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: lesson doesn't create.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/update")
    @Override
    public Response update(LessonEntity lesson) {
        try {
            lessonService.update(lesson);
            return Response.ok(lesson).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: lesson doesn't update.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Produces("application/json")
    @Path("/get/{id}")
    @Override
    public Response get(@PathParam("id")Long id) {
        try {
            LessonEntity lesson = lessonService.get(id);
            for(GroupEntity group : lesson.getGroups()){
                group.setLessons(new HashSet<LessonEntity>());
                group.setStudents(new HashSet<UserEntity>());
            }
            return Response.ok(lesson).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: lesson with such id doesn't exist.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Produces("application/json")
    @Path("/list")
    @Override
    public Response list() {
        try {
            List<LessonEntity> lessons = lessonService.list();
            return Response.ok(lessons).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: lessons don't found.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Produces("text/plain")
    @Path("/delete/{id}")
    @Override
    public Response delete(@PathParam("id")Long id) {
        try {
            lessonService.delete(id);
            return Response.ok().build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: lesson with such id doesn't delete.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
