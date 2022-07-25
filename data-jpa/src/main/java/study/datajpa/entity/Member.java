package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;

    protected Member() { // 엔티티는 기본적으로 기본 생성자가 있어야한다.
                        // JPA에서 프록시 기술을 쓸 때 private으로 막아져있음 제대로 기능을 사용하지 못한다.
    }

    public Member(String username) {
        this.username = username;
    }
}
