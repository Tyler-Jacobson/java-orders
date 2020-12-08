package com.tylerj.javaorders.controllers;

import com.tylerj.javaorders.models.Order;
import com.tylerj.javaorders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    //    http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{id}", produces = "application/json")
    public ResponseEntity<?> findOrderById(@PathVariable long id) {
        Order myOrder = orderServices.findOrderById(id);
        return new ResponseEntity<>(myOrder, HttpStatus.OK);
    }
}
