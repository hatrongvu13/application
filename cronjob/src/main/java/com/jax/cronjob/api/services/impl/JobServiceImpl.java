package com.jax.cronjob.api.services.impl;

import com.jax.cronjob.api.services.JobService;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
    @Override
    public String testCronJob() {
        return "test cron job";
    }
}
