package com.example.timer.app.service;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity(name = "CronJobs")
public class JobModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    private String jobType;
    private String jobTypeUniqueId;
    private String jobName;
    private String description;
    private String jobRequestBody;
    private String cronExpression;
    private boolean isActive;

    public JobModel(String jobType, String jobTypeUniqueId, String jobName, String description, String jobRequestBody, String cronExpression, boolean isActive) {
        this.jobType = jobType;
        this.jobTypeUniqueId = jobTypeUniqueId;
        this.jobName = jobName;
        this.description = description;
        this.jobRequestBody = jobRequestBody;
        this.cronExpression = cronExpression;
        this.isActive = isActive;
    }

}
