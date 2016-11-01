package com.sample.auth;

import com.softteco.toolset.restlet.AuthorizationException;
import com.softteco.toolset.security.AssertAuthorizedUser;

/**
 * Created on 1.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public interface AuthService {

    ProfileDto getCurrent();

    ProfileDto authorize(AuthRequestDto dto) throws AuthorizationException;
}
