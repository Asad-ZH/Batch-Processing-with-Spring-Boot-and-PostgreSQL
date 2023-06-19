package com.nerdware.oreillyspringbatch.Config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ParentJobConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private Job childJob;

    @Autowired
    private JobLauncher jobLauncher;

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("=> This is step 2");
                    return RepeatStatus.FINISHED;
                })
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job parentJob(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        Step childJobStep = new JobStepBuilder(new StepBuilder("childJobStep"))
                .job(childJob)
                .launcher(jobLauncher)
                .repository(jobRepository)
                .transactionManager(transactionManager)
                .allowStartIfComplete(true)
                .build();

        return jobBuilderFactory.get("parentJob")
                .start(step2())
                .next(childJobStep)
                .build();
    }

}
