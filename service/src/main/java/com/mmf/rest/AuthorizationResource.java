package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.LecturerService;
import com.mmf.business.StudentService;
import com.mmf.business.UserService;
import com.mmf.business.domain.Lecturer;
import com.mmf.business.domain.Student;
import com.mmf.business.domain.User;
import com.mmf.rest.util.UserRoleUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: svetlana.voyteh
 * Date: 12.05.13
 */
@Service
@Path("login")
public class AuthorizationResource {

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @Autowired
    LecturerService lecturerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@HeaderParam("Authorization") String userAgent){
        try {
            String credentials = new String(Base64.decodeBase64(userAgent.split(" ")[1]));
            int index = credentials.indexOf(":");
            String login = credentials.substring(0, index);

            User temp = userService.getUser(login);
            if(temp == null){
                throw new RestServiceException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            }

            User user = studentService.get(temp.getId());
            if (user == null){
                user = lecturerService.get(temp.getId());
                if (user == null){
                    user = temp;
                    UserRoleUtil.setRoles(user);
                } else {
                    UserRoleUtil.setRoles((Lecturer)user);
                }
            } else {
                UserRoleUtil.setRoles((Student)user);
            }
            return Response.ok(user).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }
}
