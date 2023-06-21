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
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import javax.sql.DataSource;
import java.io.File;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class WritingXMLConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public JdbcPagingItemReader<Customer> pagingItemReader() {

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
    public StaxEventItemWriter<Customer> customerItemWriterInXML() throws Exception {

        XStreamMarshaller marshaller = new XStreamMarshaller();

        marshaller.setAliases(Map.of("customer", Customer.class));

        StaxEventItemWriter<Customer> writer = new StaxEventItemWriter<>();

        writer.setRootTagName("customers");
        writer.setMarshaller(marshaller);

        String customerOutputFile = File.createTempFile("customerOutput", ".xml").getAbsolutePath();
        System.out.println(customerOutputFile);
        writer.setResource(new FileSystemResource(customerOutputFile));

        writer.afterPropertiesSet();

        return writer;
    }

    @Bean
    public FlatFileItemWriter<Customer> customerItemWriterInJson() throws Exception {
        FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<>();
        writer.setLineAggregator(new CustomerLineAggregator());

        String customerOutputFile = File.createTempFile("customerOutput", ".out").getAbsolutePath();
        System.out.println(customerOutputFile);
        writer.setResource(new FileSystemResource(customerOutputFile));

        writer.afterPropertiesSet();

        return writer;
    }

////    Custom CompositeItemWriter is used to write to both XML and JSON files
//    @Bean
//    public CompositeItemWriter<Customer> itemWriter() throws Exception {
//
//        CompositeItemWriter<Customer> itemWriter = new CompositeItemWriter<>();
//
//        itemWriter.setDelegates(java.util.List.of(customerItemWriterInJson(), customerItemWriterInXML()));
//        itemWriter.afterPropertiesSet();
//
//        return itemWriter;
//    }

    @Bean
    public ClassifierCompositeItemWriter<Customer> itemWriter() throws Exception {

       ClassifierCompositeItemWriter<Customer> itemWriter = new ClassifierCompositeItemWriter<>();
       itemWriter.setClassifier(new CustomClassifier(customerItemWriterInJson(), customerItemWriterInXML()));

       return itemWriter;
    }

    @Bean
    public Step step2() throws Exception {
        return stepBuilderFactory.get("step2")
                .<Customer, Customer>chunk(10)
                .reader(pagingItemReader())
                .writer(itemWriter())
                .stream(customerItemWriterInJson())
                .stream(customerItemWriterInXML())
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job job3() throws Exception {
        return jobBuilderFactory.get("job3")
                .start(step2())
                .build();
    }
}

