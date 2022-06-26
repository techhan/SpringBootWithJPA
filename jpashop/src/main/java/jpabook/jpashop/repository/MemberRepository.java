package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext // 스프링이 EntityManager 주입
    private final EntityManager em;


    //    @PersistenceUnit  // 엔티티 매니저 팩토리 주입
//    private EntityManagerFactory em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        // 1번째 파라미터 : JPQL , 2번째 파라미터 : 반환 타입
        // SQL와 JPQL의 차이..는 SQL은 테이블 대상, JPQL은 객체를 대상으로 한다고 보면 된다.

        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        // :name은 바인딩... setParameter로 넘겨지는 값이 바인딩 되는듯하다.
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
    }
}
