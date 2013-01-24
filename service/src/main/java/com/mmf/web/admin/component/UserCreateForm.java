package com.mmf.web.admin.component;

import com.mmf.business.service.BusinessServiceException;
import com.mmf.business.service.UserService;
import com.mmf.business.service.impl.UserServiceImpl;
import com.mmf.db.model.UserEntity;
import com.mmf.web.admin.AdminPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public class UserCreateForm extends Form<UserEntity> {
    private static final long serialVersionUID = 8046196441486400530L;

    private static final Logger LOG = LoggerFactory.getLogger(UserCreateForm.class);

    @SpringBean
    private UserService userService;

    public UserCreateForm(String id) throws BusinessServiceException {
        super(id);
        UserEntity user = userService.instance();
        setModel(new CompoundPropertyModel<UserEntity>(user));
        add(new TextField("name"));
        add(new TextField("login"));
        add(new PasswordTextField("password"));
        add(new TextField("subGroup.number"));
        add(new TextField("subGroup.year"));
        add(new Button("cancel"));

    }

    @Override
    protected void onSubmit() {
        UserEntity user = getModelObject();
        try {
            userService.create(user);
            setResponsePage(AdminPage.class);
        } catch (BusinessServiceException e) {
            LOG.error(e.getMessage());
        }
    }
}