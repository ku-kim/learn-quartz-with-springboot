package com.example.timer.playground;

import com.example.timer.info.TimerInfo;
import com.example.timer.jobs.HelloWorldJob;
import com.example.timer.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PlaygroundService {
    private final SchedulerService schedulerService;

    @Autowired
    public PlaygroundService(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    public void runHelloWorldJob() {
        TimerInfo info = new TimerInfo();
        info.setTotalFireCount(5);
        info.setRemainingFireCount(info.getTotalFireCount());
        info.setRepeatIntervalMs(2000);
        info.setInitialOffsetMs(1000);
        info.setCallbackData("callback data");

        schedulerService.schedule(HelloWorldJob.class, info);
    }

    public List<TimerInfo> getAllRunningTimers() {
        log.info("getAllRunningTimers");
        return schedulerService.getAllRunningTimers();
    }

    public TimerInfo getRunningTimer(String timerId) {
        log.info("getRunningTimer: {}", timerId);
        return schedulerService.getRunningTimer(timerId);
    }

    public Boolean deleteTimer(String timerId) {
        return schedulerService.deleteTimer(timerId);
    }
}

