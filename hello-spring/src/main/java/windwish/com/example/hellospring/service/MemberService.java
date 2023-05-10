package windwish.com.example.hellospring.service;

import org.springframework.stereotype.Service;
import windwish.com.example.hellospring.domain.Member;
import windwish.com.example.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

//@Service : 순수한 자바를 스프링이 인지
//@Service
public class MemberService {

    //회원 repository필요
    //101.클리어를 위해 : memberRepository 가져오기
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //위의 다른 repository 인스턴스를 사용 -> 문제 발생할 수 있음 ->  constructor 사용(alt + insert)
    private final MemberRepository memberRepository;

    //memberRepository를 외부에서 넣어주도록 만듦
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){

        /**
        <
        //같은 이름이 있는 중복 회원은 안됨
        Optional<Member> result = memberRepository.findByName(member.getName());
        //ifPresent : 값이 있으면
        //m : member
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        >위 코드를 아래와 같이 정리
         **/
//        memberRepository.findByName(member.getName())
//            .ifPresent(m -> {
//                throw new IllegalStateException("이미 존재하는 회원입니다.");
//            });
        //위 코드를 다시 정리
        //블럭 : Extract Method : Ctrl + Alt + M
        validateDuplicateMember(member); //중복회원 검증 메서드

        //회원 가입
        memberRepository.save(member);
        //Id 반환
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        //findAll();의 반환 타입 : List
        return memberRepository.findAll();
    }
    //그냥 만들어 놓음
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
