package com.tylerj.javaorders.controllers;

import com.tylerj.javaorders.models.Customer;
import com.tylerj.javaorders.models.Order;
import com.tylerj.javaorders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    //    POST http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = "application/json")
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customer newCustomer) {
        newCustomer.setCustcode(0);

        newCustomer = customerServices.save(newCustomer);

        // return in the header the location of the new customer
        // location -> http://localhost:2019/customers/customer/10
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/" + newCustomer.getCustcode()).build().toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    //    PUT http://localhost:2019/customers/customer/19
    @PutMapping(value = "/customer/{customerid}", consumes = "application/json")
    public ResponseEntity<?> updateFullCustomer(@Valid @RequestBody Customer updateCustomer,
                                                @PathVariable long customerid) {
        updateCustomer.setCustcode(customerid);
        customerServices.save(updateCustomer);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    //    PATCH http://localhost:2019/customers/customer/19
    @PatchMapping(value = "/customer/{customerid}", consumes = "application/json")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer updateCustomer,
                                            @PathVariable long customerid) {
        customerServices.update(updateCustomer, customerid);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    //    DELETE http://localhost:2019/customers/customer/54
    @DeleteMapping(value = "/customer/{customerid}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long customerid) {
        customerServices.delete(customerid);
        return new ResponseEntity<>(HttpStatus.OK);
    }






}
