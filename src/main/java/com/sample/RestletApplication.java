package com.sample;

import com.sample.auth.AuthResourceImpl;
import com.sample.persons.PersonsResource;
import com.sample.singup.SingupResourceImpl;
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
        return null;
    }

    @Override
    protected void createInboundRoot(final Router router) {
//        Restlet validator = new Validator();
//        router.attach("/auth", validator);
        router.attach("/auth", AuthResourceImpl.class);
        router.attach("/singup", SingupResourceImpl.class);
        router.attach("/persons", PersonsResource.class);

        /*
          ../api/swagger.js
          ../api/api-docs
        */
        attachSwaggerSpecification1(router);
        attachSwaggerSpecification2(router);
    }

    private void attachSwaggerSpecification1(Router router) {
        SwaggerSpecificationRestlet swaggerSpecificationRestlet = new SwaggerSpecificationRestlet(this);
        swaggerSpecificationRestlet.setBasePath("http://localhost:8080/sample/api/");
        swaggerSpecificationRestlet.attach(router);
    }

    private void attachSwaggerSpecification2(Router router) {
        Swagger2SpecificationRestlet restlet = new Swagger2SpecificationRestlet(this);
        restlet.setBasePath("http://localhost:8080/sample/api/");
        restlet.attach(router);
    }
}
