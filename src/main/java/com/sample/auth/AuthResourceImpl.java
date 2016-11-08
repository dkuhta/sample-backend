package com.sample.auth;

import com.google.inject.Inject;
import com.softteco.toolset.restlet.AbstractResource;
import com.softteco.toolset.restlet.AuthorizationException;
import com.softteco.toolset.restlet.UserSession;
import org.restlet.representation.Representation;

/**
 * Created on 1.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class AuthResourceImpl extends AbstractResource<UserSession> implements AuthResource {

    @Inject
    private AuthService authService;

    public ProfileDto getProfile() {
        return authService.getCurrent();
    }

    public ProfileDto login(final AuthDto dto) throws AuthorizationException {
        return authService.authorize(dto);
    }

    public void logout() {
        getHttpServletRequest().getSession().invalidate();
    }

    public Representation getRequestEntity() {
        return getRequest() == null ? null : getRequest().getEntity();
    }
}
