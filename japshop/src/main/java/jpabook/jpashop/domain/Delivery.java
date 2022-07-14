package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Delivery {

    @Id @GeneratedValue
    private Long id;

//    private String city;
////    private String street;
////    private String zipcode;

    @Embedded
    private Address address;


    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery",fetch = LAZY)
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
