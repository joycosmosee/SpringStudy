package windwish.com.example.hellospring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import windwish.com.example.hellospring.domain.Member;
import windwish.com.example.hellospring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //MemberService 만듦
//    MemberService memberSerivce = new MemberService();
    MemberService memberSerivce;

    //101.이런 것을 defendency injection이라고 함
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;
    //->동작 전 BeforeEach 만들기
    @BeforeEach
    public void beforeEach(){
        //같은 memory repository가 사용됨
        memberRepository = new MemoryMemberRepository();
        memberSerivce = new MemberService(memberRepository);
    }

    //@AfterEach : 메서드 하나 실행될 때마다 동작 실행
    @AfterEach
    public void afterEach(){
        //테스트가 하나씩 실행 될때마다 지워주는 역할
        memberRepository.clearStroe();
    }

    //회원가입
    @Test
    void join() {
        //given - when - then 문법
        //given : 상황이 주어짐
        //when : 이런 상황
        //then : 결과

        //given
        Member member = new Member();
        member.setName("hello");

        //when
        //저장한 id가 나오게
        Long saveId = memberSerivce.join(member);

        //then
        //memberService 객체에 저장한 값 불러오기
        Member findMember = memberSerivce.findOne(saveId).get();

        //import org.assertj.core.api.Assertions;
        //member의 이름이 findMember의 이름과 같냐?
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //예외 처리
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberSerivce.join(member1);
        /*
        try{
            memberSerivce.join(member2);
            fail();
        }catch(IllegalStateException e){
            //성공
            //예외 메시지가 "이미 존재하는 회원입니다" 이것과 같아야 함
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
        //try ~ catch 대신 사용
        //assertThrows -> 람다로 넘김 : member2를 넣으면 예외가 터짐
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberSerivce.join(member2));

        //예외 메시지가 "이미 존재하는 회원입니다" 이것과 같아야 함
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}