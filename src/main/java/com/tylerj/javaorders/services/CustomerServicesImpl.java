package com.tylerj.javaorders.services;

import com.tylerj.javaorders.models.Customer;
import com.tylerj.javaorders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices {
    @Autowired
    CustomerRepository customerrepos;

    @Override
    public Customer save(Customer customer) {
        return customerrepos.save(customer);
    }
}
