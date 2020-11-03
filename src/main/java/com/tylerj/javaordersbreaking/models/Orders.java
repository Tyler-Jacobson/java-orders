package com.tylerj.javaordersbreaking.models;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ordnum;
    private double ordamount;
    private double advanceamount;
    private Long custcode;
    private String orderdescription;


    public Orders() {
    }

    public Orders(double ordamount, double advanceamount, Long custcode, String orderdescription) {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.custcode = custcode;
        this.orderdescription = orderdescription;
    }
}
