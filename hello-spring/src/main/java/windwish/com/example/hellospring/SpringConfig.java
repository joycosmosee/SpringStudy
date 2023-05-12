package windwish.com.example.hellospring;

import windwish.com.example.hellospring.aop.TimeTraceAop;
import windwish.com.example.hellospring.repository.*;
import windwish.com.example.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import windwish.com.example.hellospring.repository.JdbcTemplateMemberRepository;
import windwish.com.example.hellospring.repository.JpaMemberRepository;
//import windwish.com.example.hellospring.repository.MemberRepository;
import windwish.com.example.hellospring.repository.MemoryMemberRepository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    //constructor
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //jpa
    //private final EntityManager em;
    //@Autowired
    //public SpringConfig(EntityManager em) { this.em = em; }

    //jdbctemplate 사용 - spring에서 관리해줌
    //private final DataSource dataSource;
    //public SpringConfig(DataSource dataSource) {
    //    this.dataSource = dataSource;
    //}

    //@Bean : SpringBean을 등록할 것이라는 뜻
    //MemberService 만듦
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    //Component대신 -> 이처럼 Aop 직접 등록
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

    //MemberService();에 가면 MemberRepository를 넣어줘야 함
    //@Bean
    //public MemberRepository memberRepository(){
        //인터페이스가 아닌 구현체(클래스)를 넣어줘야 함
        //return new MemoryMemberRepository();
        //return new JdbcTemplateMemberRepository(dataSource);
    //    return new JpaMemberRepository(em);
    //}
}
