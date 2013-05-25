package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.StudyService;
import com.mmf.business.domain.Study;
import com.mmf.rest.response.study.StudyResponse;
import com.mmf.rest.util.DomainUtil;
import com.mmf.rest.util.NullPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

/**
 * svetlana.voyteh
 * 06.03.13
 */
@Service
@Path("study")
public class StudyResource extends CrudResource<Study, StudyService> {

    @Autowired
    private StudyService studyService;


    @Override
    protected StudyService getService() {
        return studyService;
    }

    @Override
    protected void validate(Study domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getCurriculumId());
            DomainUtil.checkingForNotNull(domain.getGroupId());
            DomainUtil.checkingForNotNull(domain.getLecturerId());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }
    }

    @Override
    protected void updateFields(Study domain, Study newDomain) {
        domain.setCurriculumId(newDomain.getCurriculumId());
        domain.setLecturerId(newDomain.getLecturerId());
        domain.setGroupId(newDomain.getGroupId());
    }

    @Override
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id) {
        try {
            Study domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(new StudyResponse(domain)).header("Content-Encoding", "utf-8").build();
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
    public Response list() {
        try {
            List<StudyResponse> studyResponses = new LinkedList<StudyResponse>();
            for (Study study : getService().list()) {
                studyResponses.add(new StudyResponse(study));
            }
            return Response.ok(studyResponses).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/{id}/group")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudyGroup(@PathParam("id") long id) {
        try {
            Study domain = getService().get(id);
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
    public Response getStudyLecturer(@PathParam("id") long id) {
        try {
            Study domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getLecturer()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/{id}/curriculum")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurriculum(@PathParam("id") long id) {
        try {
            Study domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getCurriculum()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }
}
