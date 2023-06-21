//package com.nerdware.oreillyspringbatch.Config;
//
//import com.nerdware.oreillyspringbatch.Entity.Customer;
//import lombok.AllArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import javax.sql.DataSource;
//
//@AllArgsConstructor
//@Configuration
//public class WritingToDbConfig {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final DataSource dataSource;
//
//    @Bean
//    public FlatFileItemReader<Customer> customerFlatFileItemReader() {
//        FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
//
//        reader.setLinesToSkip(1);
//        reader.setResource(new ClassPathResource("/customer.csv"));
//
//        DefaultLineMapper<Customer> customerLineMapper = new DefaultLineMapper<>();
//
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        tokenizer.setNames("id", "firstName", "lastName", "birthdate");
//
//        customerLineMapper.setLineTokenizer(tokenizer);
//        customerLineMapper.setFieldSetMapper(new CustomerFieldSetMapper());
//        customerLineMapper.afterPropertiesSet();
//
//        reader.setLineMapper(customerLineMapper);
//
//        return reader;
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<Customer> customerJdbcItemWriter() {
//        JdbcBatchItemWriter<Customer> writer = new JdbcBatchItemWriter<>();
//
//        writer.setDataSource(this.dataSource);
//        writer.setSql("INSERT INTO customer VALUES (:id, :firstName, :lastName, :birthdate)");
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//        writer.afterPropertiesSet();
//
//        return writer;
//    }
//
//    @Bean
//    public Step step() {
//        return stepBuilderFactory.get("step")
//                .<Customer, Customer>chunk(10)
//                .reader(customerFlatFileItemReader())
//                .writer(customerJdbcItemWriter())
//                .allowStartIfComplete(true)
//                .build();
//    }
//
//    @Bean
//    public Job job1() {
//        return jobBuilderFactory.get("job2")
//                .start(step())
//                .build();
//    }
//}