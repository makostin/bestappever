package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.DepartmentService;
import com.mmf.business.GroupService;
import com.mmf.business.SpecialtyService;
import com.mmf.business.domain.Department;
import com.mmf.business.domain.SpecialtyInfo;
import com.mmf.rest.response.InitialDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

/**
 * svetlana.voyteh
 * 12.03.13
 */
@Service
@Path("initialData")
public class InitialDataResource {
    // todo: remove constants
    public static final Integer COURSE_AMOUNT = 5;
    public static final Integer GROUP_AMOUNT = 10;
    public static final List<String> SUBGROUPS = Arrays.asList("a", "b");
    public static final String FIRST_SEMESTER_START = "01.09";
    public static final String FIRST_SEMESTER_END = "25.12";
    public static final String SECOND_SEMESTER_START = "01.02";
    public static final String SECOND_SEMESTER_END = "25.05";

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SpecialtyService specialtyService;
    

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInitialData(){
        try {
            List<Department> departmentList = departmentService.list();
            List<SpecialtyInfo> specialties = specialtyService.getSpecialtyInfos();

            InitialDataResponse initialData = new InitialDataResponse();
            initialData.setCourseAmount(COURSE_AMOUNT);
            initialData.setGroupAmount(GROUP_AMOUNT);
            initialData.setSubGroups(SUBGROUPS);
            initialData.setFirstSemesterStart(FIRST_SEMESTER_START);
            initialData.setFirstSemesterEnd(FIRST_SEMESTER_END);
            initialData.setSecondSemesterStart(SECOND_SEMESTER_START);
            initialData.setSecondSemesterEnd(SECOND_SEMESTER_END);
            initialData.setDepartments(departmentList);
            initialData.setSpecialties(specialties);
            
            return Response.ok(initialData).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }
}
