package com.tylerj.javaorders.services;

import com.tylerj.javaorders.models.Customer;

import java.util.List;

public interface CustomerServices {
    Customer save(Customer customer);

    List<Customer> findAllOrders();

    Customer findCustomerById(long id);

    List<Customer> findCustomersByNameLike(String name);
}
