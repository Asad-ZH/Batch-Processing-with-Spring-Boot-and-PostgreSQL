package com.nerdware.oreillyspringbatch.Config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChildJobConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1a(){
        return stepBuilderFactory.get("step1a")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> This is step 1a");
                    return RepeatStatus.FINISHED;
                })
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job childJob(){
        return jobBuilderFactory.get("childJob")
                .start(step1a())
                .build();
    }
}