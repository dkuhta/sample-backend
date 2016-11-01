package com.sample.auth;

import com.google.inject.Inject;
import com.softteco.toolset.restlet.AuthorizationException;
import com.softteco.toolset.restlet.UserSession;
import com.softteco.toolset.security.AssertAuthorizedUser;

/**
 * Created on 1.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class AuthServiceBean implements AuthService {

    @Inject
    private UserSession userSession;

    @AssertAuthorizedUser
    @Override
    public ProfileDto getCurrent() {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setUsername(userSession.getUsername());
        return profileDto;
    }

    @Override
    public ProfileDto authorize(final AuthRequestDto dto) throws AuthorizationException {
        userSession.setUsername(dto.getUsername());
        ProfileDto profileDto = new ProfileDto();
        profileDto.setUsername(userSession.getUsername());
        return profileDto;
    }
}
