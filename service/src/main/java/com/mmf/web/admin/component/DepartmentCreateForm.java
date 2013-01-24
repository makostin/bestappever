package com.mmf.web.admin.component;

import com.mmf.business.service.BusinessServiceException;
import com.mmf.business.service.DepartmentService;
import com.mmf.db.model.DepartmentEntity;
import com.mmf.web.admin.AdminPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public class DepartmentCreateForm extends Form<DepartmentEntity>{

    private static final long serialVersionUID = 2549667062618136820L;
    private static final Logger LOG = LoggerFactory.getLogger(DepartmentCreateForm.class);

    @SpringBean
    private DepartmentService departmentService;

    public DepartmentCreateForm(String id) throws BusinessServiceException {
        super(id);
        DepartmentEntity department = departmentService.instance();
        setModel(new CompoundPropertyModel<DepartmentEntity>(department));
        add(new TextField("name"));
        add(new Button("cancel"));

    }

    @Override
    protected void onSubmit() {
        DepartmentEntity department = getModelObject();
        try {
            departmentService.create(department);
            setResponsePage(AdminPage.class);
        } catch (BusinessServiceException e) {
            LOG.error(e.getMessage());
        }
    }
}
