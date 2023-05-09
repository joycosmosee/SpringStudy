package windwish.com.example.hellospring.controller.repository;

import windwish.com.example.hellospring.controller.domain.Member;

import java.util.*;

//implements 하는 법1 : implements는 인터페이스(Interface)를 구현하는 것을 나타내는 키워드
//implements 하는 법2 : 윈도우 : art + enter : 전체 선택
public class MemoryMemberRepository implements MemberRepository{

    //Map : 키(Key)와 값(Value)의 쌍으로 데이터를 저장하는 자료구조
    //Long 타입 키, Member 타입 값, 변수 store
    //HashMap : Java에서 제공하는 해시 테이블 기반의 Map 인터페이스 구현체
    private static Map<Long, Member> store = new HashMap<>();
    //sequence란? 0,1,2 키 값 들 생성
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        //sequence 값 올려주기
        //member 구조체에서 넘어온 id 값 올려줌
        member.setId(++sequence);
        //store에 저장
        store.put(member.getId(), member);
        //저장된 값 반환
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //store.get(id) : store에서 get을 사용해서 id를 꺼내옴
        //Optional.ofNullable : null이어도 값사서 반환함
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //람다 함수 사용
        //store.values().stream() : 루프 돌림
        //member.getName()과 파라미터에서 넘어온 name과 같은지 확인
        //findAny(); 같은 값 찾으면 찾은 값 반환 : 하나 찾으면 바로 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //반환에서 list 많이 씀 : store의 값을 반환
        return new ArrayList<>(store.values());
    }

    //store를 비워주는 interface
    public void clearStroe(){
        store.clear();
    }
}
