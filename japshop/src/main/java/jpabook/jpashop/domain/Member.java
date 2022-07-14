package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(length = 10) // 이런 걸 명시해두면 개발자가 굳이 DB를 뒤져보지 않아도 제약조건을 알 수 있다.
    private String name;
//    private String city;
//    private String street;
//    private String zipcode;

    // 사실 Member에 order List가 있는 설계는 좋은 설계는 아니지만
    // (Order에 MEMBER_ID란 외래키가 있어서 거기에서 회원에 대한 주문 내역을 뽑을 수 있음)
    // 예시를 위해 추가한다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Embedded
    private Address address;


    public Member() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
