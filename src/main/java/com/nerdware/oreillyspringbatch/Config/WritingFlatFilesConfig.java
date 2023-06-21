package com.nerdware.oreillyspringbatch.Config;

import com.nerdware.oreillyspringbatch.Entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.io.File;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class WritingFlatFilesConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public JdbcPagingItemReader<Customer> pagingItemReader(){
        JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(this.dataSource);
        reader.setFetchSize(10);
        reader.setRowMapper(new CustomerRowMapper());

        PostgresPagingQueryProvider queryProvider = new PostgresPagingQueryProvider();
        queryProvider.setSelectClause("select id, first_name, last_name, birthdate");
        queryProvider.setFromClause("from customer");

        Map<String, Order> sortKeys = Map.of("id", Order.ASCENDING);
        queryProvider.setSortKeys(sortKeys);
        reader.setQueryProvider(queryProvider);

        return reader;
    }

    @Bean
    public FlatFileItemWriter<Customer> customerItemWriter() throws Exception {
        FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<>();
//        writer.setLineAggregator(new PassThroughLineAggregator<>());
        writer.setLineAggregator(new CustomerLineAggregator());

        String customerOutputFile = File.createTempFile("customerOutput", ".out").getAbsolutePath();
        System.out.println(customerOutputFile);
        writer.setResource(new FileSystemResource(customerOutputFile));

        writer.afterPropertiesSet();

        return writer;
    }

    @Bean
    public FilteringItemProcessor itemProcessor(){
        return new FilteringItemProcessor();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(10)
                .reader(pagingItemReader())
                .processor(itemProcessor())
                .writer(customerItemWriter())
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job job2() throws Exception {
        return jobBuilderFactory.get("job2")
                .start(step1())
                .build();
    }
}
