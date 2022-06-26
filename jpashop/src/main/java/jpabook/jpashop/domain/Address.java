package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    /**
     * 값 타입은 변경 불가능하게 설계해야한다.
     * @Setter를 제거하고, 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스를 만들어야 한다.
     * JPA 스펙상 엔티티나 임베디드 타입(@Embeddable)은 자바 기본 생성자를 public 또는
     * protected로 설정해야한다.
     * 이런 제약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때 리플랙션 같은 기술을
     * 사용할 수 있도록 지원해야 하기 때문..
     */

    private String city;
    private String street;
    private String zipcode;

    protected Address() {} // 기본 생성자 없고 아래 생성자만 있으면 빨간줄 뜬다.

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
