//package com.nerdware.oreillyspringbatch.Config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class StepTransitionConfiguration {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Step step1(){
//        return stepBuilderFactory.get("step1")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("StepTransitionConfiguration.step1()");
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }
//
//    @Bean
//    public Step step2(){
//        return stepBuilderFactory.get("step2")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("StepTransitionConfiguration.step2()");
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }
//
//    @Bean
//    public Step step3(){
//        return stepBuilderFactory.get("step3")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("StepTransitionConfiguration.step3()");
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }
//
//    @Bean
//    public Job transitionJobSimpleNext(){
//        return jobBuilderFactory.get("transitionJobSimpleNext")
//                .start(step1())
//                .on("COMPLETED").to(step2())
//                .from(step2()).on("COMPLETED").to(step3())
//                .from(step3()).end()
//                .build();
//    }
//}
