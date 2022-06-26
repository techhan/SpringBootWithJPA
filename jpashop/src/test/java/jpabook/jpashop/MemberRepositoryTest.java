package jpabook.jpashop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberRepositoryTest
{
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional // 이게 Test 코드에 적혀있으면 테스트가 끝나자마자 Rollback 해버림
    @Rollback(false) // Rollback 제거.. 데이터 DB에 남아있음.
    public void testMember() throws Exception
    {
        //given
        Member member = new Member();
        member.setUsername("userA");

        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        //then
        assertEquals(findMember.getId(), member.getId());
        assertEquals(findMember.getUsername(), member.getUsername());
        assertEquals(findMember, member);
    }
}