package com.example.timer.app.service;

import com.example.timer.app.job.HelloWorldJob2;
import com.example.timer.app.util.JobUtil;
import com.example.timer.tutorial.jobs.HelloWorldJob;
import com.example.timer.tutorial.service.SimpleTriggerListner;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Slf4j
public class JobScheduler {

    private final Scheduler scheduler;

    public JobScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }


    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public void createJob(JobModel jobModel)  {
        try {
            // job 유효성 검증
            JobDetail jobDetail = scheduler.getJobDetail(new JobKey(String.valueOf(jobModel.getJobId())));
            if (jobDetail != null) throw new RuntimeException();

            switch (jobModel.getJobType()) {
                case "helloworld" -> {
                    JobDetail jobDetail1 = JobUtil.buildJobDetail(HelloWorldJob2.class, jobModel, jobModel.getJobId());
                    Trigger trigger = JobUtil.buildTrigger(HelloWorldJob2.class, jobModel, jobModel.getJobId());

                    scheduler.scheduleJob(jobDetail1, trigger);
                }
                default -> {
                    throw new RuntimeException();
                }
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage());
        }
    }
}
