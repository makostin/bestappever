package com.mmf.web;

import com.mmf.web.admin.AdminPage;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public class ScheduleWebApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return AdminPage.class;
    }

    @Override
    protected void init() {
        super.init();
        getMarkupSettings().setDefaultMarkupEncoding("ISO-8859-1");
        getRequestCycleSettings().setResponseRequestEncoding("ISO-8859-1");
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }
}
