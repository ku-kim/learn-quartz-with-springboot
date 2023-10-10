package com.example.timer.app.job;

import com.example.timer.app.service.JobModel;
import com.example.timer.app.util.JobConstants;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloWorldJob2 implements Job {
    @Override
    public void execute(JobExecutionContext context)  {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        JobModel jobModel = (JobModel) jobDataMap.get(JobConstants.JOB_DATA);
        String jobID = String.valueOf(jobDataMap.get(JobConstants.JOB_ID));

        log.info("The JobID is `" + jobID + "` --- Job name is `" + jobModel.getJobName() + "`");
        log.info("HelloWorld!!! " + jobID);
    }}
