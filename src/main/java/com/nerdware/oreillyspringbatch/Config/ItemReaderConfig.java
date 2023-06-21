//package com.nerdware.oreillyspringbatch.Config;
//
//import com.nerdware.oreillyspringbatch.Domain.Customer;
//import com.nerdware.oreillyspringbatch.Domain.CustomerRowMapper;
//import lombok.AllArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JdbcCursorItemReader;
//import org.springframework.batch.item.database.JdbcPagingItemReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@AllArgsConstructor
//@Configuration
//public class ItemReaderConfig {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final DataSource dataSource;
//
//    @Bean
//    public JdbcCursorItemReader<Customer> cursorItemReader(){
//
//        JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<>();
//
//        reader.setSql("select id, firstName, lastName, birthdate from customer order by lastName, firstName");
//        reader.setDataSource(this.dataSource);
//        reader.setRowMapper(new CustomerRowMapper());
//
//        return reader;
//    }
//
//    @Bean
//    public JdbcPagingItemReader<Customer> pagingItemReader(){
//        return null;
//        /*
//        flatfile
//        xml
//        multiple files (read it with flatfileitemreader)
//        */
//    }
//
//    @Bean
//    public ItemWriter<Customer> itemWriter(){
//        return list -> {
//            for(Customer curItem : list){
//                System.out.println(curItem.toString());
//            }
//        };
//    }
//
//    @Bean
//    public Step step1(){
//        return stepBuilderFactory.get("step1")
//                .<Customer,Customer> chunk(10)
//                .reader(cursorItemReader())
//                .writer(itemWriter())
//                .build();
//    }
//
//    @Bean
//    public Job interfacesJob(){
//        return jobBuilderFactory.get("interfacesJob")
//                .start(step1())
//                .build();
//    }
//
//}
