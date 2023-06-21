package com.nerdware.oreillyspringbatch.Config;

import org.springframework.batch.item.ItemWriter;
import com.nerdware.oreillyspringbatch.Entity.Customer;
import org.springframework.classify.Classifier;


public class CustomClassifier implements Classifier<Customer, ItemWriter<? super Customer>> {
    private ItemWriter<Customer> evenItemWriter;
    private ItemWriter<Customer> oddItemWriter;

    public CustomClassifier(ItemWriter<Customer> evenItemWriter, ItemWriter<Customer> oddItemWriter) {
        this.evenItemWriter = evenItemWriter;
        this.oddItemWriter = oddItemWriter;
    }
    @Override
    public ItemWriter<? super Customer> classify(Customer customer) {
        return customer.getId() % 2 == 0 ? evenItemWriter : oddItemWriter;
    }
}
