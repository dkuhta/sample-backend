package com.sample.mail;

import com.google.inject.Inject;
import com.sample.I18nServiceBean;
import com.softteco.toolset.mail.MailService;

import java.util.HashMap;

/**
 * Created on 9.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class MailNotificationServiceBean implements MailNotificationService {

    @Inject
    private MailService mailAsyncService;

    @Inject
    private I18nServiceBean i18nServiceBean;


    @Override
    public void sendWelcome(final String to) {
        final String subject = i18nServiceBean.getMessage(I18nServiceBean.BUNDLE_MAIL, "mail.welcome.subject");
        final String body = i18nServiceBean.getMessage(I18nServiceBean.BUNDLE_MAIL, "mail.welcome.body", new HashMap<String, String>() {
            {
                put("name", "Name");
            }
        });
        mailAsyncService.send(to, subject, body);
    }

    @Override
    public void sendResetPassword(final String to, final String newPassword) {
        final String subject = i18nServiceBean.getMessage(I18nServiceBean.BUNDLE_MAIL, "mail.password.reset.subject");
        final String body = i18nServiceBean.getMessage(I18nServiceBean.BUNDLE_MAIL, "mail.password.reset.body", new HashMap<String, String>() {
            {
                put("newPassword", newPassword);
            }
        });
        mailAsyncService.send(to, subject, body);
    }
}
