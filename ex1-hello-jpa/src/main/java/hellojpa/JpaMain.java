package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
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
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity","street", "123"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("라떼");

            member.getAddressHistory().add(new AddressEntity("old_1","street", "123"));
            member.getAddressHistory().add(new AddressEntity("old_2","street", "123"));

            em.persist(member);

            em.flush();
            em.clear();


            System.out.println("============= START ===============");
            Member findMember = em.find(Member.class, member.getId());

//            List<Address> addressHistory = findMember.getAddressHistory();
//            for(Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
            Address oldAdd = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", oldAdd.getStreet(),oldAdd.getZipcode() ));

            // 치킨 -> 한식
            // String이기 때문에 그냥 기존에 있던 치킨을 지우고 한식을 추가해주는 방법 밖에는 없다.
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");

            // 컬렉션 remove()는 기본적으로 매개변수로 주어진 값으로 기존에 있는 값을 찾을때 equals()로 동작한다.
            // 그래서 remove()로 삭제할 때 삭제하고 싶은 값과 똑같이 넣어주면 된다.
            // 컬렉션의 경우 일반적인 equals()를 사용하는 경우 같은 값이라도 false를 반환하게 된다.
            // 그래서 override 된 equals()와 hashCode가 꼭 필수이다.
            findMember.getAddressHistory().remove(new AddressEntity("old_1","street", "123"));
            findMember.getAddressHistory().add(new AddressEntity("newCity1","street", "123"));




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
