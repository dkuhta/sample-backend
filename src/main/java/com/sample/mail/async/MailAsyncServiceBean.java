package com.sample.mail.async;

import com.google.inject.Inject;
import com.softteco.toolset.scheduler.SchedulerManager;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;

/**
 * Created on 8.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class MailAsyncServiceBean implements MailAsyncService {

    @Inject
    private SchedulerManager schedulerManager;

    @Override
    public void send(final String email, final String subject, final String body) {
        final JobDetail jobDetail = JobBuilder.newJob(MailSendingJob.class).withIdentity("mail_" + System.currentTimeMillis()).build();
        jobDetail.getJobDataMap().put(MailSendingJob.EMAIL, email);
        jobDetail.getJobDataMap().put(MailSendingJob.SUBJECT, subject);
        jobDetail.getJobDataMap().put(MailSendingJob.BODY, body);
        schedulerManager.addJob(jobDetail);
    }
}
