package com.tylerj.javaorders.services;

import com.tylerj.javaorders.models.Customer;
import com.tylerj.javaorders.models.Order;
import com.tylerj.javaorders.models.Payment;
import com.tylerj.javaorders.repositories.CustomerRepository;
import com.tylerj.javaorders.repositories.OrderRepository;
import com.tylerj.javaorders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "orderServices")
public class OrderServicesImpl implements OrderServices{

    @Autowired
    OrderRepository orderrepos;

    @Autowired
    CustomerRepository customerrepos;

    @Autowired
    PaymentRepository paymentrepos;

    @Override
    public Order findOrderById(long id) {
        return orderrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found"));
    }

    @Override
    public Order save(Order order) {
        Order newOrder = new Order();

        if (order.getOrdnum() != 0) {
            orderrepos.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found"));

            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());

        // Many to Many
        newOrder.getPayments().clear();
        for (Payment p : order.getPayments()) {
            Payment newPay = paymentrepos.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment with id " + p.getPaymentid() + " Not Found"));

            newOrder.getPayments().add(newPay);
        }

        // Many to One
        long findCustomerCode = order.getCustomer().getCustcode();
        Customer myCustomer = customerrepos.findById(findCustomerCode)
                .orElseThrow(() -> new EntityNotFoundException("Customer with Custcode " + findCustomerCode + " Not Found"));

        newOrder.setCustomer(myCustomer);



        return orderrepos.save(newOrder);
    }

    @Override
    public void delete(long id) {
        orderrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " Not Found"));
        orderrepos.deleteById(id);
    }
}
