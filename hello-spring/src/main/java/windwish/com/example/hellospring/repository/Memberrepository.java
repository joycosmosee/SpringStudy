package windwish.com.example.hellospring.repository;

import windwish.com.example.hellospring.domain.Member;

public class Memberrepository {

    public interface MemberRepository{
        Member save(Member member)
    }
}
