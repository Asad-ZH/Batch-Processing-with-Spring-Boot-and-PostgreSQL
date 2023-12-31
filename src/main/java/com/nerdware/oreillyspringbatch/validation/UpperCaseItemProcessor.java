package com.nerdware.oreillyspringbatch.validation;

import com.nerdware.oreillyspringbatch.Entity.Customer;
import org.springframework.batch.item.ItemProcessor;

public class UpperCaseItemProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
        return new Customer(customer.getId(),
                customer.getFirstName().toUpperCase(),
                customer.getLastName(),
                customer.getBirthdate());
    }
}
