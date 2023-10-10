package com.example.timer.app.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobResponse {
    private String jobName;
    private boolean isActive;
    private long jobID;
}
