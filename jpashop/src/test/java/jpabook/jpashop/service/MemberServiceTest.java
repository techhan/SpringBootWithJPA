package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    //@Autowired EntityManager em;


    @Test
    //@Rollback(false)
    public void 회원가입() throws Exception {
        /** 로그에 select만 찍히고 insert문이 없다.
         * @GenerateValue 전략에서는 insert문이 나가지 않는다.
         * DB 트랜잭션이 커밋될 때 플러시가 되면서 DB insert 쿼리가 쭉 나간다.
         * @Transactional의 경우는 기본적으로 테스트에서 롤백을 시키므로
         * insert 쿼리를 눈으로 보고싶으면 직접 설정(@Rollback(false))해줘야햔다.
         * 또는 EntityManager로 직접 플러시 처리를 해주면된다.
         */


        //given
        Member member = new Member();
        member.setName("Lee");

        //when
        Long savedId = memberService.join(member);

        //then
        //em.flush(); 쿼리를 강제로 날린다.
        assertThat(member).isEqualTo(memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("Lee1");

        Member member2 = new Member();
        member2.setName("Lee1");

        //when
        memberService.join(member1);
        //memberService.join(member2); // 예외가 발생해야한다.

        //then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }

}