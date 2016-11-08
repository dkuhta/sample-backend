package com.sample;

import com.sample.auth.AuthService;
import com.sample.auth.AuthServiceBean;
import com.sample.util.ApplicationUtils;
import com.softteco.toolset.AbstractApplicationModule;
import com.softteco.toolset.restlet.AbstractRestletApplication;
import com.softteco.toolset.restlet.UserSession;

import java.io.File;

/**
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
        String configFile = "/opt/sample/config.properties";
        if (!new File(configFile).exists()) {
            configFile = ApplicationUtils.getAppPath() + "config.properties";
        }
        return new String[]{
                ApplicationUtils.getAppPath() + "config-common.properties",
                configFile
        };
    }

    /*@Override
    protected void configureApplication() {
        bind(AuthService.class).to(AuthServiceBean.class);
    }*/
}
