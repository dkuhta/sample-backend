package com.sample.auth;

import com.google.inject.Inject;
import com.softteco.toolset.restlet.AbstractResource;
import com.softteco.toolset.restlet.AuthorizationException;
import com.softteco.toolset.restlet.UserSession;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * Created on 1.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class AuthResource extends AbstractResource<UserSession> {

    @Inject
    private AuthService authService;

    @Get("json")
    public ProfileDto getProfile() {
        return authService.getCurrent();
    }

    @Post("json")
    public ProfileDto authorize(final AuthRequestDto dto) throws AuthorizationException {
        return authService.authorize(dto);
    }

    @Delete("json")
    public void logout() {
        getHttpServletRequest().getSession().invalidate();
    }
}
