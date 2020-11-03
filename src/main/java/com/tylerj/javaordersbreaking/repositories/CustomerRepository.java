package com.tylerj.javaordersbreaking.repositories;

import com.tylerj.javaordersbreaking.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
