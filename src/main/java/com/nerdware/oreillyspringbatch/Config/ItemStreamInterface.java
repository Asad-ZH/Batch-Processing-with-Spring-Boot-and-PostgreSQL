//package com.nerdware.oreillyspringbatch.Config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@AllArgsConstructor
//public class ItemStreamInterface {
//
//    private JobBuilderFactory jobBuilderFactory;
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    @StepScope
//    public StatefulItemReader statefulItemReader() {
//        List<String> items = new ArrayList<>(100);
//
//        for(int i = 0; i < 200; i++) {
//            items.add(String.valueOf(i));
//        }
//
//        return new StatefulItemReader(items);
//    }
//
//    @Bean
//    public ItemWriter itemWriter(){
//        return (ItemWriter<String>) items -> {
//            for(String curItem : items){
//                System.out.println("curItem = " + curItem);
//            }
//        };
//    }
//
//    @Bean
//    public Step step(){
//        return stepBuilderFactory.get("step")
//                .<String,String> chunk(10)
//                .reader(statefulItemReader())
//                .writer(itemWriter())
//                .stream(statefulItemReader())
//                .build();
//    }
//
//    @Bean
//    public Job statefulJob() {
//        return jobBuilderFactory.get("statefulJob")
//                .start(step())
//                .build();
//    }
//
//}
