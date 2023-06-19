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
//public class FlowLastConfiguration {
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Bean
//    public Step flowLastStep(){
//        return stepBuilderFactory.get("flowLastStep")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("flowLastStep");
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }
//
//    @Bean
//    public Job flowLastJob(Flow flow){
//        return jobBuilderFactory.get("flowLastJob")
//                .start(flowLastStep())
//                .on("COMPLETED").to(flow)
//                .end()
//                .build();
//    }
//
//}
