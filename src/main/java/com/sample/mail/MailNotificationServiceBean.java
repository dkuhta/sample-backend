package com.sample.mail;

import com.google.inject.Inject;
import com.sample.mail.async.MailAsyncService;

/**
 * Created on 9.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class MailNotificationServiceBean implements MailNotificationService {

    @Inject
    private MailAsyncService mailAsyncService;

    @Override
    public void sendWelcomeEmail(final String to) {
        mailAsyncService.send(to, "welcome body", "welcome subject");
    }
}
