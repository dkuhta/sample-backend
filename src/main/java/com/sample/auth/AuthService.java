package com.sample.auth;

import com.google.inject.ImplementedBy;
import com.softteco.toolset.restlet.AuthorizationException;

/**
 * Created on 1.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(AuthServiceBean.class)
public interface AuthService {

    ProfileDto getCurrent();

    ProfileDto authorize(AuthDto dto) throws AuthorizationException;
}
