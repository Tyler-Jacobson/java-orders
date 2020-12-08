package com.tylerj.javaorders.controllers;

import com.tylerj.javaorders.models.Customer;
import com.tylerj.javaorders.models.Order;
import com.tylerj.javaorders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    //    http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> listAllOrders() {
        List<Customer> myList = customerServices.findAllOrders();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    //    http://localhost:2019/customers/customer/7
    @GetMapping(value = "/customer/{customerid}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long customerid) {
        Customer myCustomer = customerServices.findCustomerById(customerid);
        return new ResponseEntity<>(myCustomer, HttpStatus.OK);
    }

    //    http://localhost:2019/customers/namelike/mes
    @GetMapping(value = "/namelike/{name}", produces = "application/json")
    public ResponseEntity<?> findCustomersByNameLike(@PathVariable String name) {
        List<Customer> myList = customerServices.findCustomersByNameLike(name);
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }




}
