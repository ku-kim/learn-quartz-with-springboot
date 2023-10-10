package com.example.timer.tutorial.service;

import com.example.timer.tutorial.info.TimerInfo;
import com.example.timer.tutorial.util.TimerUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SchedulerService {
    private final Scheduler scheduler;

    @Autowired
    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void schedule(final Class jobClass, final TimerInfo info) {
        JobDetail jobDetail = TimerUtils.buildJobDetail(jobClass, info);
        Trigger trigger = TimerUtils.buildTrigger(jobClass, info);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    public List<TimerInfo> getAllRunningTimers() {
        try {
            return scheduler.getJobKeys(GroupMatcher.anyGroup())
                    .stream()
                    .map(jobKey -> {
                        try {
                            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                            return (TimerInfo) jobDetail.getJobDataMap().get(jobKey.getName());
                        } catch (SchedulerException e) {
                            log.error(e.getMessage(), e);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
    public TimerInfo getRunningTimer(String timerId) {
        try {
            JobDetail jobDetail = scheduler.getJobDetail(new JobKey(timerId));

            if (Objects.isNull(jobDetail)) {
                return null;
            }

            return (TimerInfo) jobDetail.getJobDataMap().get(timerId);
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public void updateTimer(String timerId, TimerInfo info) {
        try {
            JobDetail jobDetail = scheduler.getJobDetail(new JobKey(timerId));

            if (Objects.isNull(jobDetail)) {
                return;
            }

            jobDetail.getJobDataMap().put(timerId, info);
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    public Boolean deleteTimer(String timerId) {
        try {
            return scheduler.deleteJob(new JobKey(timerId));
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }
    // @PostConstruct
    // public void init() {
    //     try {
    //         scheduler.start();
    //         scheduler.getListenerManager().addTriggerListener(new SimpleTriggerListner(this));
    //     } catch (SchedulerException e) {
    //         log.error(e.getMessage(), e);
    //         throw new RuntimeException(e);
    //     }
    // }
    //
    // @PreDestroy
    // public void preDestroy() {
    //     try {
    //         scheduler.shutdown();
    //     } catch (SchedulerException e) {
    //         log.error(e.getMessage(), e);
    //         throw new RuntimeException(e);
    //     }
    // }
}
