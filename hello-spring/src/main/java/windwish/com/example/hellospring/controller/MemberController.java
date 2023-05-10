package windwish.com.example.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import windwish.com.example.hellospring.domain.Member;
import windwish.com.example.hellospring.service.MemberService;

//@Contoller : 스프링 컨테이너가 관리함
@Controller
public class MemberController {

    //문제 : new : 여러 서비스에서 MemberService를 가져가 쓸 수 있는 문제 -> 하나만 쓰면 됨
    //private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    //생성자
    //@Autowired : MemberService를 가져와서 사용할 수 있음
    //거의 바뀌지 않음 : 접근 못하게 해야한다는 거 인지
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // ----------------------------------------------------- //

    //회원 웹 기능 - 등록
    //GetMapping 하기
    @GetMapping("/members/new")
    public String createForm(){
        //템플릿 -> 이 위치로 이동(폴더와 파일 만들기) : createMemberForm.html으로 이동
        return "members/createMemberForm";
    }

    //public String create(MemberForm form) : form을 만듦
    //MemberForm : 들어가보면 name 임 -> html의 name값이 들어옴(setName을 통해 들어옴)
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        //getName을 통해 가져옴
        member.setName(form.getName());

        System.out.println(" member = " + member.getName());

        //회원가입 : join
        memberService.join(member);

        //회원 가입으로 돌려보냄
        return "redirect:/";
    }


}
