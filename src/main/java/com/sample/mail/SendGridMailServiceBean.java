package com.sample.mail;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.softteco.toolset.mail.MailService;

/**
 * Created by dkuhta on 3.8.15.
 */
public class SendGridMailServiceBean extends AbstractSendGridMailService implements MailService {

    @Inject
    @Named("sendgrid.api.key")
    private String apiKey;
    @Inject
    @Named("sendgrid.from.email")
    private String from;

    @Override
    protected String getApiKey() {
        return apiKey;
    }

    @Override
    protected String getFrom() {
        return from;
    }
}
