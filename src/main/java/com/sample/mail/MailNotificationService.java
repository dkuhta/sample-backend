package com.sample.mail;

import com.google.inject.ImplementedBy;

/**
 * Created on 9.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(MailNotificationServiceBean.class)
public interface MailNotificationService {

    void sendWelcome(String to);

    void sendResetPassword(String to, String password);
}
