//package com.nerdware.oreillyspringbatch.Config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FlowFirstConfiguration {
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Bean
//    public Step flowFirstStep(){
//        return stepBuilderFactory.get("flowFirstStep")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("flowFirstStep");
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }
//
//    @Bean
//    public Job flowFirstJob(Flow flow){
//        return jobBuilderFactory.get("flowFirstJob")
//                .start(flow)
//                .next(flowFirstStep())
//                .end()
//                .build();
//    }
//}
