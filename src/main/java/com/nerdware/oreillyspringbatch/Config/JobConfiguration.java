//package com.nerdware.oreillyspringbatch.Config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//// Item Reader with StatelessItemReader
//
//@Configuration
//public class JobConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public JobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//
//    @Bean
//    public StatelessItemReader statelessItemReader() {
//        List<String> data = new ArrayList<>(3);
//
//        data.add("Foo");
//        data.add("Bar");
//        data.add("Baz");
//
//        return new StatelessItemReader(data);
//    }
//
//    @Bean
//    public Step step1(){
//        return stepBuilderFactory.get("step1")
//                .<String,String> chunk(3)
//                .reader(statelessItemReader())
//                .writer(list -> {
//                    for(String curItem : list){
//                        System.out.println("curItem = " + curItem);
//                    }
//                })
//                .allowStartIfComplete(true)
//                .build();
//    }
//
//    @Bean
//    public Job interfacesJob() {
//        return jobBuilderFactory.get("interfacesJob")
//                .start(step1())
//                .build();
//    }
//}
