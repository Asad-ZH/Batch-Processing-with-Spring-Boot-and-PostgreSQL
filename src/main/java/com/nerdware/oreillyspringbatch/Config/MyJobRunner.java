package com.nerdware.oreillyspringbatch.Config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyJobRunner implements CommandLineRunner {

    @Autowired
    public JobLauncher jobLauncher;

    @Autowired
    public Job batchJob;


    @Override
    public void run(String... args) throws Exception {
        JobExecution StepTransitionJobRunner = jobLauncher.run(batchJob, new JobParametersBuilder().toJobParameters());
    }
}
