package com.nerdware.oreillyspringbatch.Config;

import com.nerdware.oreillyspringbatch.Entity.Customer;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

public class CustomerValidator implements Validator<Customer> {
    @Override
    public void validate(Customer customer) throws ValidationException {
        if(customer.getFirstName().startsWith("A")){
            throw new ValidationException("First names that begin with A are invalid: " + customer);
        }
    }
}
