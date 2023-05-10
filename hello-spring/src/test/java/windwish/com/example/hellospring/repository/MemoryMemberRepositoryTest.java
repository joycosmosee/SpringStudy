package windwish.com.example.hellospring.repository;

//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import windwish.com.example.hellospring.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//참고 : TDD : 테스트 주도 개발 : 테스트부터 만듦

//@Test는 서로 의존성 없이 설계되어야 함
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //@AfterEach : 메서드 하나 실행될 때마다 동작 실행
    @AfterEach
    public void afterEach(){
        //테스트가 하나씩 실행 될때마다 지워주는 역할
        repository.clearStroe();
    }

    //save 기능이 잘 작동하는지 테스트
    @Test
    public void save(){
        Member member = new Member();
        //이름 셋팅
        member.setName("spring");

        //repository에 memeber를 save함함
       repository.save(member);

        //검증
        //get으로 꺼낼 수 있음
        Member result = repository.findById(member.getId()).get();
        //result와 member가 같냐?
        //System.out.println("result = " + (result == member));
        //위와 같은 기능 : Assertions
        //성공하면 녹색 불 뜸
        //Assertions.assertEquals(member, result);

        //import org.assertj.core.api.Assertions; - 이 라이브러리로 import
        //member와 result 같은 지 비교
        //Assertions.assertThat(member).isEqualTo(result);

        //Assertions에 alt+enter : static을 만들 수 있음
        assertThat(member).isEqualTo(result);
    }

    //findByName test
    @Test
    public void findByName(){
        //spring1 회원
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //spring2 회원
        //단축키 : shift + f6
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        //비교
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        //assertThat(result.size()).isEqualTo(3);
    }
}
