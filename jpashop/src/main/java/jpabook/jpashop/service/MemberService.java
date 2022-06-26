package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 트랜잭션, 영속성 컨텍스트
// 조회같은 읽기의 경우에는 readOnly = true를 넣어주는 것이 좋다.
// readOnly를 사용하면 영속성 컨텍스트를 플러시 하지 않으므로 약간의 성능 향상이 된다.
@RequiredArgsConstructor // final이 붙어있는 필드와 관련된 생성자 주입코드를 자동으로 만들어준다.
public class MemberService {

    private final MemberRepository memberRepository;

////    @Autowired  >> 생성자가 하나 밖에 없는 경우에는 Autowired를 쓰지 않아도 스프링에서 자동으로 붙여준다.
//    public MemberService(MemberRepository memberRepository) {
//        // 최근에는 생성자 주입을 많이 사용한다.
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    @Transactional // readOnly 생략 시 false가 기본값이다.
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
