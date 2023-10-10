package com.example.timer.app.repository;

import com.example.timer.app.service.JobModel;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<JobModel, Long> {
}
