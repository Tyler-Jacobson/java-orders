package com.tylerj.javaorders.services;

import com.tylerj.javaorders.models.Customer;

import java.util.List;

public interface CustomerServices {


    List<Customer> findAllOrders();

    Customer findCustomerById(long id);

    List<Customer> findCustomersByNameLike(String name);



    Customer save(Customer customer);

    Customer update(Customer customer, long id);

    void delete(long id);

}
