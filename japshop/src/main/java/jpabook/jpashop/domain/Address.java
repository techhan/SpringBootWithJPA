package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {

    @Column(length = 10)
    private String city;

    @Column(length = 20)
    private String street;

    @Column(length = 5)
    private String zipcode;

    // 값 타입을 사용하면 의미있는 메서드 만들기 가능
    public String fullAddress() {
        return getCity() + " " + getStreet() + " " + getZipcode();
    }


    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    //Use getters during code generation을 체크해주는 게 좋다.
    //위 옵션을 체크하면 필드에 직접 접근하는 것이 아니라 getter를 통해 접근한다.
    //만약 위 옵션을 체크하지 않을 시 프록시 사용할 떄 equals()가 제대로 동작 안한다.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}
