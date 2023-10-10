package com.example.timer.app.util;


import com.example.timer.app.controller.request.JobRequest;
import com.example.timer.app.service.JobModel;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public final class JobUtil {
	public static JobDetail buildJobDetail(final Class jobClass, final JobModel jobModel, final long jobID)  {
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put(JobConstants.JOB_DATA, jobModel);
		jobDataMap.put(JobConstants.JOB_ID, jobID);
		return JobBuilder.newJob(jobClass).withIdentity(String.valueOf(jobID)).setJobData(jobDataMap).build();
	}

	public static Trigger buildTrigger(final Class jobClass, final JobModel jobModel, final long jobID)  {
		CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(jobModel.getCronExpression());
		return TriggerBuilder.newTrigger().withIdentity(String.valueOf(jobID)).withSchedule(builder).startNow().build();
	}

}
