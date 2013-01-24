package com.mmf.web.admin.component;

import com.mmf.business.service.BusinessServiceException;
import com.mmf.business.service.DisciplineService;
import com.mmf.db.model.DisciplineEntity;
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
public class SubjectCreateForm extends Form<DisciplineEntity>{

    private static final long serialVersionUID = 8845150992321865922L;
    private static final Logger LOG = LoggerFactory.getLogger(SubjectCreateForm.class);

    @SpringBean
    private DisciplineService disciplineService;

    public SubjectCreateForm(String id) throws BusinessServiceException {
        super(id);
        DisciplineEntity discipline = disciplineService.instance();
        setModel(new CompoundPropertyModel<DisciplineEntity>(discipline));
        add(new TextField("name"));
        add(new Button("cancel"));

    }

    @Override
    protected void onSubmit() {
        DisciplineEntity discipline = getModelObject();
        try {
            disciplineService.create(discipline);
            setResponsePage(AdminPage.class);
        } catch (BusinessServiceException e) {
            LOG.error(e.getMessage());
        }
    }
}
