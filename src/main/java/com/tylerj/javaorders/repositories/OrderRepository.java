package com.tylerj.javaorders.repositories;

import com.tylerj.javaorders.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
