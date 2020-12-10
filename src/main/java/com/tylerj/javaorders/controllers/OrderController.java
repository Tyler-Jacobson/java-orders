package com.tylerj.javaorders.controllers;


import com.tylerj.javaorders.models.Order;
import com.tylerj.javaorders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    //    POST http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = "application/json")
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody Order newOrder) {
        newOrder.setOrdnum(0);

        newOrder = orderServices.save(newOrder);

        // location -> http://localhost:2019/orders/order/10
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/" + newOrder.getOrdnum()).build().toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //    PUT http://localhost:2019/orders/order/63
    @PutMapping(value = "/order/{orderid}", consumes = "application/json")
    public ResponseEntity<?> updateFullOrder(@Valid @RequestBody Order updateOrder,
                                                @PathVariable long orderid) {
        updateOrder.setOrdnum(orderid);
        orderServices.save(updateOrder);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    DELETE http://localhost:2019/orders/order/58
    @DeleteMapping(value = "/order/{orderid}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long orderid) {
        orderServices.delete(orderid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
