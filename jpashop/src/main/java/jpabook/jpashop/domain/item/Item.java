package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
    /** 모든 연관 관계는 자연 로딩으로 설정..!
     * 즉시 로딩 - 테이블 하나를 조회하면 그와 연관된 테이블까지 모두 한꺼번에 조회
     * 즉시 로딩(EAGER)은 예측이 어렵고 어떤 SQL이 실행될지 추측하기 어렵다.
     * 특히 JPQL을 실행할 때 N + 1 문제가 자주 발생한다.
     * 실무에서 모든 연관관계는 지연로딩(LAZY)로 설정해야한다.
     * 연관된 엔티티를 함께 DB에서 조회해야 하면, fetch join 또는 엔티티 그래프 기능을 사용한다.
     * @XToOne(OneToOne, ManyToOne) 관계는 기본이 즉시 로딩이므로 직접 자연 로딩으로 설정해야 한다.
     */

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // 비즈니스 로직
    // 데이터를 가지고 있는 쪽에 관련 비즈니스 로직이 있어야 객체지향적으로도 맞고, 응집력이 좋다.

    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
