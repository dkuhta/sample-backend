package com.sample.accounts.singup;

import com.google.inject.Inject;
import com.sample.accounts.AccountService;
import com.softteco.toolset.restlet.AbstractResource;
import com.softteco.toolset.restlet.UserSession;

/**
 * Created on 7.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class SingupResourceBean extends AbstractResource<UserSession> implements SingupResource {
    
    @Inject
    private AccountService accountService;
    
    @Override
    public void singup(final SingupDto dto) {
        accountService.singup(dto);
    }
}
