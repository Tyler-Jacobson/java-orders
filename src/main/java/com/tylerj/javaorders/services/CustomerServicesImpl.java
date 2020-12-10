package com.tylerj.javaorders.services;

import com.tylerj.javaorders.models.Agent;
import com.tylerj.javaorders.models.Customer;
import com.tylerj.javaorders.models.Order;
import com.tylerj.javaorders.models.Payment;
import com.tylerj.javaorders.repositories.AgentRepository;
import com.tylerj.javaorders.repositories.CustomerRepository;
import com.tylerj.javaorders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices {
    @Autowired
    CustomerRepository customerrepos;

    @Autowired
    PaymentRepository paymentrepos;

    @Autowired
    AgentRepository agentrepos;

    @Override
    public List<Customer> findAllOrders() {
        List<Customer> list = new ArrayList<>();
        customerrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customer findCustomerById(long id) {
        return customerrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
    }

    @Override
    public List<Customer> findCustomersByNameLike(String name) {
        return customerrepos.findByCustnameContainingIgnoringCase(name);
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();

        if (customer.getCustcode() != 0) {
            customerrepos.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found"));

            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());


        // One to Many
        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders()) {

            Order newOrder = new Order();
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setCustomer(newCustomer);
            newOrder.setOrderdescription(o.getOrderdescription());


            for (Payment p : o.getPayments()) {

                Payment newPayment = paymentrepos.findById(p.getPaymentid()).orElseThrow(() -> new EntityNotFoundException("Payment with paymentid " + p.getPaymentid() + " Not Found"));

                newOrder.getPayments().add(newPayment);
            }



            newCustomer.getOrders().add(newOrder);
        }

        // Many to One
        long findAgentCode = customer.getAgent().getAgentcode();
        Agent myAgent = agentrepos.findById(findAgentCode).orElseThrow(() -> new EntityNotFoundException("Agent with agent code " + findAgentCode + " Not Found"));

        newCustomer.setAgent(myAgent);



        return customerrepos.save(newCustomer);
    }

    // for update?
    // customerrepos.findById(customer.getCustcode())
    //                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found"))

//    @Transactional
//    @Override
//    public Customer update(Customer customer, long id) {
//
//        Customer currentCustomer = customerrepos.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " Not Found"));
//
//
//        if (customer.getCustname() != null) {
//
//        }
//
//
//        return null;
//    }
    @Transactional
    @Override
    public Customer update(Customer customer, long id) {


        Customer currentCustomer = customerrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " Not Found"));

        if (customer.getCustname() != null) {
            currentCustomer.setCustname(customer.getCustname());
        }

        if (customer.getCustcity() != null) {
            currentCustomer.setCustcity(customer.getCustcity());
        }

        if (customer.getWorkingarea() != null) {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if (customer.getCustcountry() != null) {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }

        if (customer.getGrade() != null) {
            currentCustomer.setGrade(customer.getGrade());
        }

        if (customer.hasvalueforopeningamt) {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if (customer.hasvalueforrecieveamt) {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if (customer.hasvalueforpaymentamt) {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if (customer.hasvalueforoutstandingamt) {
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        if (customer.getPhone() != null) {
            currentCustomer.setPhone(customer.getPhone());
        }



        // One to Many
        if (customer.getOrders().size() > 0) {
            currentCustomer.getOrders().clear();
            for (Order o : customer.getOrders()) {

                Order newOrder = new Order();
                newOrder.setOrdamount(o.getOrdamount());
                newOrder.setAdvanceamount(o.getAdvanceamount());
                newOrder.setCustomer(currentCustomer);
                newOrder.setOrderdescription(o.getOrderdescription());


                for (Payment p : o.getPayments()) {

                    Payment newPayment = paymentrepos.findById(p.getPaymentid()).orElseThrow(() -> new EntityNotFoundException("Payment with paymentid " + p.getPaymentid() + " Not Found"));

                    newOrder.getPayments().add(newPayment);
                }


                currentCustomer.getOrders().add(newOrder);
            }
        }

        // Many to One
        if (customer.getAgent() != null) {
            long findAgentCode = customer.getAgent().getAgentcode();
            Agent myAgent = agentrepos.findById(findAgentCode).orElseThrow(() -> new EntityNotFoundException("Agent with agent code " + findAgentCode + " Not Found"));

            currentCustomer.setAgent(myAgent);
        }




        return customerrepos.save(currentCustomer);
    }


    @Override
    public void delete(long id) {
        if (customerrepos.findById(id).isPresent()) {
            customerrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer with id " + id + " Not Found");
        }
    }
}
