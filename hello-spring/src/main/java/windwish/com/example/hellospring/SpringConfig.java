package windwish.com.example.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import windwish.com.example.hellospring.repository.MemberRepository;
import windwish.com.example.hellospring.repository.MemoryMemberRepository;
import windwish.com.example.hellospring.service.MemberService;

@Configuration
public class SpringConfig {

    //@Bean : SpringBean을 등록할 것이라는 뜻
    //MemberService 만듦
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    //MemberService();에 가면 MemberRepository를 넣어줘야 함
    @Bean
    public MemberRepository memberRepository(){
        //인터페이스가 아닌 구현체(클래스)를 넣어줘야 함
        return new MemoryMemberRepository();
    }
}
