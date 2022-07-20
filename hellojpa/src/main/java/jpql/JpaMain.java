package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            em.persist(team);

            Member member = new Member();
            member.setUsername("Admin1");
            member.setTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("Admin2");
            member2.setTeam(team);
            em.persist(member2);

            em.flush();
            em.clear();


            String query = "select m.username from Team t join t.members m";
            List resultList = em.createQuery(query, Collection.class).getResultList();

            for (Object o : resultList) {
                System.out.println("o = " + o);
            }


//            Member findMember = result.get(0);
//            findMember.setAge(20);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 엔티티 매니저 종료
            em.close();
        }
        // 엔티티 매니저 팩토리 종료
        emf.close();
    }
}
