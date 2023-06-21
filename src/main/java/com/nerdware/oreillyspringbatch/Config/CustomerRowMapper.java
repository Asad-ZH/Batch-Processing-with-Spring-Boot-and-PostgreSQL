package com.nerdware.oreillyspringbatch.Config;

import com.nerdware.oreillyspringbatch.Entity.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer>
{
    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Customer(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("birthdate")
        );
    }
}
