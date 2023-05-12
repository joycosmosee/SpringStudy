package windwish.com.example.hellospring.repository;

import windwish.com.example.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//springData Api는 구현체가 자동으로 만들어줌
//interface의 확장은 extends
//키 : Member
//type : Member의 id : Long type
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,
        Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
