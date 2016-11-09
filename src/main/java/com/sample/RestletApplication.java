package com.sample;

import com.sample.accounts.AccountResourceBean;
import com.sample.auth.AuthResourceBean;
import com.sample.singup.SingupResourceBean;
import com.softteco.toolset.restlet.AbstractRestletApplication;
import com.softteco.toolset.restlet.AbstractStatusService;
import org.restlet.ext.swagger.Swagger2SpecificationRestlet;
import org.restlet.ext.swagger.SwaggerSpecificationRestlet;
import org.restlet.routing.Router;

/**
 * @author serge
 */

public class RestletApplication extends AbstractRestletApplication {

    @Override
    protected AbstractStatusService createStatusService() {
        return new StatusService();
    }

    @Override
    protected void createInboundRoot(final Router router) {
        router.attach("/auth", AuthResourceBean.class);
        router.attach("/singup", SingupResourceBean.class);
        router.attach("/account", AccountResourceBean.class);

        /*
          ../api/swagger.js
          ../api/api-docs
        */
        attachSwaggerSpecification1(router);
        attachSwaggerSpecification2(router);
    }

    private void attachSwaggerSpecification1(Router router) {
        SwaggerSpecificationRestlet swaggerSpecificationRestlet = new SwaggerSpecificationRestlet(this);
        swaggerSpecificationRestlet.setBasePath(getBasePath());
        swaggerSpecificationRestlet.attach(router);
    }

    private void attachSwaggerSpecification2(Router router) {
        Swagger2SpecificationRestlet restlet = new Swagger2SpecificationRestlet(this);
        restlet.setBasePath(getBasePath());
        restlet.attach(router);
    }

    private String getBasePath() {
        return "http://82.196.12.148:8080/zappy-backend/api/";
    }
}
