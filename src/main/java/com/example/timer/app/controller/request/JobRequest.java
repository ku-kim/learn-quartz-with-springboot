package com.example.timer.app.controller.request;

import com.example.timer.app.service.JobModel;
import com.example.timer.app.service.JobType;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class JobRequest {

    @Nullable
    private long jobID;
    private String jobType;
    private String jobTypeUniqueId;
    private String jobName;
    private String description;
    private String jobRequestBody;
    private String cronExpression;
    private boolean isActive;

    public JobModel toEntity() {
        JobModel jobModel = new JobModel(this.jobType, this.jobTypeUniqueId, this.jobName, this.description,
                                         this.jobRequestBody, this.cronExpression, this.isActive);

        return jobModel;
    }
}
