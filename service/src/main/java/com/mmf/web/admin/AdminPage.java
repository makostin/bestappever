package com.mmf.web.admin;

import com.mmf.business.service.BusinessServiceException;
import com.mmf.web.MasterPage;
import com.mmf.web.admin.component.*;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;


/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public class AdminPage extends MasterPage{

    private static final long serialVersionUID = 1641682258165949700L;

    public AdminPage() throws BusinessServiceException {
        add(new Label("message", "Hello World!"));
        add(new SubjectCreateForm("SubjectForm"));
        add(new DepartmentCreateForm("DepartmentForm"));
        add(new ClassroomCreateForm("ClassroomForm"));
        add(new UserCreateForm("UserForm"));
        add(new LecturerCreateForm("LecturerForm"));
    }
}
