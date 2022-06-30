package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // 엔티티 매니저 팩토리 생성, 파라미터로 넘어가는 값은 persistence.xml에서 persistence-unit의 name 값을 넘겨주면 된다.
        // emf의 경우 애플리케이션 로딩 시점에 딱 하나만 만들면 된다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUsername("A");


            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");

            // DB 시퀀스 : 1    | 필드 id : 1
            // DB 시퀀스 : 51   | 필드 id : 2
            // DB 시퀀스 : 51   | 필드 id : 3

            em.persist(member1); // 1, 51
            em.persist(member2); // 메모리
            em.persist(member3); // 메모리


            for(int i = 4; i <= 48; i++) {
                Member member = new Member();
                member.setUsername(i+"");
                em.persist(member);
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // 엔티티 매니저 종료
            em.close();
        }
        // 엔티티 매니저 팩토리 종료
        emf.close();
    }
}
