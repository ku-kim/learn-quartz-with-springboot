package com.example.timer.app.service;

import com.example.timer.app.controller.request.JobRequest;
import com.example.timer.app.controller.response.JobResponse;
import com.example.timer.app.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class APISchedulerService {

    private final JobRepository jobRepository;
    private final JobScheduler jobScheduler;

    public JobModel createJob(JobRequest jobRequest) {
        // jobRequest valid (생략)

        jobRequest.setActive(true);

        JobModel entity = jobRequest.toEntity();

        JobModel jobModel = jobRepository.save(entity);

        jobScheduler.createJob(jobModel);

        return jobModel;
    }

}
