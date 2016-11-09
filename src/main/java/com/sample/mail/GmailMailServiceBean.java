package com.sample.mail;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.softteco.toolset.mail.AbstractMailServiceBean;
import com.softteco.toolset.mail.MailService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

/**
 * Created on 9.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class GmailMailServiceBean extends AbstractMailServiceBean implements MailService {

    @Inject
    @Named("mail.username")
    private String username;
    @Inject
    @Named("mail.password")
    private String password;

    @Override
    protected void configureEmail(final Email email) throws EmailException {
        email.setAuthenticator(new DefaultAuthenticator(username, password));
        email.setFrom(username);
    }
}
