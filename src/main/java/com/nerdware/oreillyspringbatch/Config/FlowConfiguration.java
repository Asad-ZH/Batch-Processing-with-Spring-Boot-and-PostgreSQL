//package com.nerdware.oreillyspringbatch.Config;
//
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//@Configuration
//public class FlowConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Step flowStep1(){
//        return stepBuilderFactory.get("flowStep1")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("flowStep1");
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }
//
//    @Bean
//    public Step flowStep2(){
//        return stepBuilderFactory.get("flowStep1")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("flowStep2");
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }
//
//    @Bean
//    public Flow foo(){
//        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("foo");
//
//        flowBuilder.start(flowStep1())
//                .next(flowStep2())
//                .end();
//
//        return flowBuilder.build();
//    }
//}
