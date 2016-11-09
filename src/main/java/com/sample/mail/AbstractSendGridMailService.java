package com.sample.mail;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import com.softteco.toolset.mail.MailService;

import java.text.MessageFormat;

/**
 * Created on 8.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public abstract class AbstractSendGridMailService implements MailService {

    protected abstract String getApiKey();

    protected abstract String getFrom();

    @Override
    public void send(final String to, final String subject, final String html) {
        SendGrid sendgrid = new SendGrid(getApiKey());
        SendGrid.Email email = buildEmail(to, subject, html);
        try {
            System.out.println(MessageFormat.format("Sending mail to {0}.", to));
            sendgrid.send(email);
            System.out.println(MessageFormat.format("Mail has been send to {0}.", to));
        } catch (SendGridException e) {
            System.out.println(MessageFormat.format("Problem with sending email to {0}.", to));
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void sendTestEmail() {

    }

    private SendGrid.Email buildEmail(final String to, final String subject, final String html) {
        SendGrid.Email email = new SendGrid.Email();
        email.addTo(to);
        email.setFrom(getFrom());
        email.setSubject(subject);
        email.setHtml(html);
        return email;
    }
}
