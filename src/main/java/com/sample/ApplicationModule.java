package com.sample;

import com.sample.auth.AuthService;
import com.sample.auth.AuthServiceBean;
import com.softteco.toolset.AbstractApplicationModule;
import com.softteco.toolset.restlet.AbstractRestletApplication;
import com.softteco.toolset.restlet.UserSession;
import com.sample.util.ApplicationUtils;

/**
 *
 * @author serge
 */
public class ApplicationModule extends AbstractApplicationModule {

    @Override
    protected String getJpaUnitName() {
        return "sample";
    }

    @Override
    protected Class<? extends AbstractRestletApplication> getRestletApplication() {
        return RestletApplication.class;
    }

    @Override
    protected Class<? extends UserSession> getUserSessionClass() {
        return UserSessionBean.class;
    }

    @Override
    protected String[] getPropertiesFiles() {
        return new String[] {
                ApplicationUtils.getAppPath() + "config.properties"
        };
    }

    @Override
    protected void configureApplication() {
        bind(AuthService.class).to(AuthServiceBean.class);
    }
}
