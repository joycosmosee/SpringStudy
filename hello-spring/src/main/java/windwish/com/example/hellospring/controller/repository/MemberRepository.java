package windwish.com.example.hellospring.controller.repository;

import windwish.com.example.hellospring.controller.domain.Member;

import java.util.List;
import java.util.Optional;

//회원 객체 저장소
public interface MemberRepository {
    //회원 객체 저장소 save
    Member save(Member member);
    //Optional : 파일이 비었을 때 null을 Optional로 감싸서 반환하는 방법을 많이 씀
    // findById : id로 회원을 찾는 방법 만들기
    // findByName : name으로 회원을 찾는 방법 만들기
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //findAll : 지금까지 저장된 모든 리스트를 반환
    List<Member> findAll();
}
