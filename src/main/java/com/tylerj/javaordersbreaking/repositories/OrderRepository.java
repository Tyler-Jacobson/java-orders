package com.tylerj.javaordersbreaking.repositories;

import com.tylerj.javaordersbreaking.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
