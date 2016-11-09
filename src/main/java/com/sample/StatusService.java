package com.sample;

import com.softteco.toolset.restlet.AbstractStatusService;
import com.softteco.toolset.security.exception.ResourceNotFoundException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityExistsException;

public class StatusService extends AbstractStatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusService.class);

    /*@Inject(optional = true)
    @Named("showstacktrace")
    private boolean showStackTrace;*/

    @Override
    protected boolean showStackTrace() {
        //TODO fix config loading
        return true;
    }

    @Override
    protected Status getStatus(final Throwable t) {
        if (t instanceof SecurityException) {
            return Status.CLIENT_ERROR_UNAUTHORIZED;
        }
        if (t instanceof ResourceNotFoundException) {
            return Status.CLIENT_ERROR_NOT_FOUND;
        }
        if (t instanceof EntityExistsException) {
            return Status.CLIENT_ERROR_CONFLICT;
        }

        LOGGER.error("Request error", t);
        t.printStackTrace(System.out);
        return Status.SERVER_ERROR_INTERNAL;
    }

    @Override
    protected void append(final Throwable throwable, final JSONObject response) throws JSONException {
        super.append(throwable, response);

        if (throwable != null) {
            response.put("error", throwable.getMessage());
        } else {
            response.put("error", "Unknown error.");
        }
    }
}
