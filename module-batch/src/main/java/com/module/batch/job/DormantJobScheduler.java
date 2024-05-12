package com.module.batch.job;//package com.homework.jinsimver.dormant;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DormantJobScheduler {

    @Autowired
    private final JobLauncher jobLauncher;

    @Autowired
    private final Job job;


    @Scheduled(cron = "5/30 * * * * *")
    public void dormantJobRun() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLocalDateTime("time", LocalDateTime.now())
                .toJobParameters();
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
    }

}
