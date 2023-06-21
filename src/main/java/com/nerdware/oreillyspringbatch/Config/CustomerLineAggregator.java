package com.nerdware.oreillyspringbatch.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerdware.oreillyspringbatch.Entity.Customer;
import org.springframework.batch.item.file.transform.LineAggregator;

public class CustomerLineAggregator implements LineAggregator<Customer> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String aggregate(Customer customer) {
        try {
            return objectMapper.writeValueAsString(customer);
        } catch (Exception e) {
            throw new RuntimeException("Unable to serialize Customer", e);
        }

    }
}
