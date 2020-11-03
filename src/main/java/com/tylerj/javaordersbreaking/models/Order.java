package com.tylerj.javaordersbreaking.models;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
public class Order {

//ordnum, ordamount, advanceamount, custcode, orderdescription
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ordnum;

    private double ordamount;

    private double advanceamount;

    @Column(nullable = false)
    private Long custcode;

    private String orderdescription;

//    @ManyToOne
//    @JoinColumn(name = "custcode", nullable = false)
//    private Customer customer;


    public Order() {
    }
// ORDERS (ordnum, ordamount, advanceamount, custcode, orderdescription)
    public Order(double ordamount, double advanceamount, Long custcode, String orderdescription) {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.custcode = custcode;
        this.orderdescription = orderdescription;
    }

    public Long getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(Long ordnum) {
        this.ordnum = ordnum;
    }

    public double getOrdamount() {
        return ordamount;
    }

    public void setOrdamount(double ordamount) {
        this.ordamount = ordamount;
    }

    public double getAdvanceamount() {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount) {
        this.advanceamount = advanceamount;
    }

    public Long getCustcode() {
        return custcode;
    }

    public void setCustcode(Long custcode) {
        this.custcode = custcode;
    }

    public String getOrderdescription() {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription) {
        this.orderdescription = orderdescription;
    }
}
