package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.NoteService;
import com.mmf.business.domain.Note;
import com.mmf.rest.response.note.NoteResponse;
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
@Path("note")
public class NoteResource extends CrudResource<Note, NoteService> {

    @Autowired
    private NoteService noteService;

    @Override
    protected NoteService getService() {
        return noteService;
    }

    @Override
    protected void validate(Note domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getScheduleId());
            DomainUtil.checkingForNotNull(domain.getDate());
            DomainUtil.checkingForNotNull(domain.getText());
            DomainUtil.checkingForNotNull(domain.getUserId());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }
    }

    @Override
    protected void updateFields(Note domain, Note newDomain) {
        domain.setScheduleId(newDomain.getScheduleId());
        domain.setUserId(newDomain.getUserId());
        domain.setDate(newDomain.getDate());
        domain.setText(newDomain.getText());
        domain.setColor(newDomain.getColor());
    }

    @Override
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id){
        try {
            Note domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(new NoteResponse(domain)).header("Content-Encoding", "utf-8").build();
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
            List<NoteResponse> noteResponses = new LinkedList<NoteResponse>();
            for(Note note : getService().list()){
                noteResponses.add(new NoteResponse(note));
            }
            return Response.ok(noteResponses).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/{id}/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") long id){
        try {
            Note domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getUser()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/{id}/schedule")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSchedule(@PathParam("id") long id){
        try {
            Note domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getSchedule()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

}
