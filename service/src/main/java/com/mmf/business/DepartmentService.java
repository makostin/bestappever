package com.mmf.business;

import com.mmf.business.domain.Department;
import com.mmf.rest.response.department.DepartmentResponse;

import java.util.List;

/**
 * svetlana.voyteh
 * 05.03.13
 */
public interface DepartmentService extends CrudService<Long, Department>{

    List<DepartmentResponse> listDepartments() throws BusinessServiceException;
}
