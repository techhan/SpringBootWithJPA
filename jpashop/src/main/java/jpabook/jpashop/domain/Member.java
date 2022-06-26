package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    /** 하이버네이트 기존 구현 : 엔티티의 필드명을 그대로 테이블의 컬럼명으로 사용
     * 스프링 부트 신규 설정(엔티티(필드) -> 테이블(컬럼))
     * 1. 카멜 케이스 -> 언더스코어 (memberId -> member_id)
     * 2. .(점) -> _(언더스코어)
     * 3. 대문자 -> 소문자
     *
     */

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // 컬렉션은 필드에서 초기화하자.
    // 컬렉션은 필드에서 바로 초기화 하는 것이 null 문제에서 대해서 안전하다.
    // 컬렉션을 꺼내서 수정하거나 하면 하이버네이트의 메커니즘대로 흘러가지 못하므로 수정 절대 금지!!!
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}
