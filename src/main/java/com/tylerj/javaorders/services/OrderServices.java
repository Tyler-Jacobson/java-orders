package com.tylerj.javaorders.services;

import com.tylerj.javaorders.models.Order;

public interface OrderServices {
    Order save(Order order);

    Order findOrderById(long id);
}
