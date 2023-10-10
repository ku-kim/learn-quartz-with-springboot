package com.example.timer.app.controller;

import com.example.timer.app.controller.request.JobRequest;
import com.example.timer.app.service.APISchedulerService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/scheduler")
@RequiredArgsConstructor
public class APISchedulerController {

    private final APISchedulerService apiSchedulerService;

    @PostMapping("/jobs")
    public ResponseEntity<Object> createJob(@RequestBody JobRequest jobRequest) {
        return ResponseEntity.created(URI.create("/api/scheduler/jobs"))
                             .body(apiSchedulerService.createJob(jobRequest));
    }

}
