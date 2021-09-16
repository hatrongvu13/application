package com.jax.cronjob.api.services.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TelegramSchedule {

//    @Scheduled(cron = "${}", zone =  "${}")
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        System.out.println("FixedDelayTask "+ System.currentTimeMillis() / 1000);
    }

    @Scheduled(cron = "15 * * * * ?")
    public void  scheduleCron() {
        System.out.println(LocalDateTime.now());
    }
}
