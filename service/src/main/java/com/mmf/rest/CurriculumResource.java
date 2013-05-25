package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.CurriculumService;
import com.mmf.business.domain.Classroom;
import com.mmf.business.domain.Curriculum;
import com.mmf.rest.response.curriculum.CurriculumResponse;
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
 * User: svetlana.voyteh
 * Date: 17.05.13
 */
@Service
@Path("curriculum")
public class CurriculumResource extends CrudResource<Curriculum, CurriculumService>{

    @Autowired
    private CurriculumService curriculumService;

    @Override
    protected CurriculumService getService() {
        return curriculumService;
    }

    @Override
    protected void validate(Curriculum domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getDisciplineId());
            DomainUtil.checkingForNotNull(domain.getSpecialtyId());
            DomainUtil.checkingForNotNull(domain.getHours());
            DomainUtil.checkingForNotNull(domain.getExam());
            DomainUtil.checkingForNotNull(domain.getSetoff());
            DomainUtil.checkingForNotNull(domain.getSemester());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }
    }

    @Override
    protected void updateFields(Curriculum domain, Curriculum newDomain) {
        domain.setDisciplineId(newDomain.getDisciplineId());
        domain.setSpecialtyId(newDomain.getSpecialtyId());
        domain.setSemester(newDomain.getSemester());
        domain.setHours(newDomain.getHours());
        domain.setExam(newDomain.getExam());
        domain.setSetoff(newDomain.getSetoff());
    }

    @Override
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id){
        try {
            Curriculum domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(new CurriculumResponse(domain)).header("Content-Encoding", "utf-8").build();
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
            List<CurriculumResponse> curriculumResponses = new LinkedList<CurriculumResponse>();
            for(Curriculum curriculum : getService().list()){
                curriculumResponses.add(new CurriculumResponse(curriculum));
            }
            return Response.ok(curriculumResponses).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/{id}/discipline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscipline(@PathParam("id") long id){
        try {
            Curriculum domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getDiscipline()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/{id}/specialty")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecialty(@PathParam("id") long id){
        try {
            Curriculum domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getSpecialty()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }
}
