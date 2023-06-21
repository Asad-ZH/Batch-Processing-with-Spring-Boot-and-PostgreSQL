package com.nerdware.oreillyspringbatch.validation;

import com.nerdware.oreillyspringbatch.Entity.Customer;
import org.springframework.batch.item.ItemProcessor;

public class FilteringItemProcessor implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(Customer customer) throws Exception {

        if(customer.getId() % 2 == 0){
            return customer;
        } else {
            return null;
        }
    }
}
