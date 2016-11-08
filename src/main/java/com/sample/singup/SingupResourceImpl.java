package com.sample.singup;

import com.softteco.toolset.restlet.AbstractResource;
import com.softteco.toolset.restlet.UserSession;

/**
 * Created on 7.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class SingupResourceImpl extends AbstractResource<UserSession> implements SingupResource {
    @Override
    public void singup(final SingupDto dto) {

    }
}
