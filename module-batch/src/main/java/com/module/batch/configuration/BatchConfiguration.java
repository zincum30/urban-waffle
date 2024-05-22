package com.module.batch.configuration;


import com.module.batch.event.UpdateCompletionConsumer;
import com.module.batch.job.bean.InactiveUserBean;
import com.module.batch.job.bean.MultiDataSourceBean;
import com.module.batch.dto.InactiveUserDto;
import com.module.batch.dto.MultiDataSourceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {


    private static final int CHUNK_SIZE = 10;

    private final UpdateCompletionConsumer updateCompletionConsumer;

    private final InactiveUserBean inactiveUserBean;
    private final MultiDataSourceBean multiDataSourceBean;


    @Bean
    public Job dormantJob(JobRepository jobRepository, Step dormantJobStep) {
        return new JobBuilder("dormantJob", jobRepository)
                .start(dormantJobStep)
                .incrementer(new RunIdIncrementer())
                .build();

    }

    @Bean
    @JobScope
    public Step dormantJobStep(
            JobRepository jobRepository,
            PlatformTransactionManager platformTransactionManager
    ) {
        Step dormantJobStep = new StepBuilder("dormantJobStep", jobRepository)
                .<InactiveUserDto, MultiDataSourceDto>chunk(CHUNK_SIZE, platformTransactionManager)
                .reader(inactiveUserBean)
                .processor(multiDataSourceBean)
                .writer(multiDataSourceBean)
                .listener(updateCompletionConsumer)
                .transactionManager(platformTransactionManager)
                .startLimit(1)
                .build();
        return dormantJobStep;
    }


}
