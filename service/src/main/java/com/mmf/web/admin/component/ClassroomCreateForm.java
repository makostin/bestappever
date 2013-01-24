package com.mmf.web.admin.component;

import com.mmf.business.service.BusinessServiceException;
import com.mmf.business.service.ClassroomService;
import com.mmf.business.service.impl.ClassroomServiceImpl;
import com.mmf.db.model.ClassroomEntity;
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
public class ClassroomCreateForm extends Form<ClassroomEntity> {
    private static final long serialVersionUID = -6234846875939764621L;

    private static final Logger LOG = LoggerFactory.getLogger(ClassroomCreateForm.class);

    @SpringBean
    private ClassroomService classroomService;

    public ClassroomCreateForm(String id) throws BusinessServiceException {
        super(id);
        ClassroomEntity classroom = classroomService.instance();
        setModel(new CompoundPropertyModel<ClassroomEntity>(classroom));
        add(new TextField("number"));
        add(new Button("cancel"));

    }

    @Override
    protected void onSubmit() {
        ClassroomEntity classroom = getModelObject();
        try {
            classroomService.create(classroom);
            setResponsePage(AdminPage.class);
        } catch (BusinessServiceException e) {
            LOG.error(e.getMessage());
        }
    }
}
