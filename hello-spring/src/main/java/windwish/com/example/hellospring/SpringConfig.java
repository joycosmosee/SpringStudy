package windwish.com.example.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import windwish.com.example.hellospring.repository.JdbcTemplateMemberRepository;
import windwish.com.example.hellospring.repository.JpaMemberRepository;
import windwish.com.example.hellospring.repository.MemberRepository;
import windwish.com.example.hellospring.repository.MemoryMemberRepository;
import windwish.com.example.hellospring.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final EntityManager em;
    public SpringConfig(EntityManager em) { this.em = em; }

    //jdbctemplate 사용 - spring에서 관리해줌
    //private final DataSource dataSource;
    //public SpringConfig(DataSource dataSource) {
    //    this.dataSource = dataSource;
    //}

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
        //return new MemoryMemberRepository();
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
