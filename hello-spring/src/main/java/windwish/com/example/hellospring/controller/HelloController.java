package windwish.com.example.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //웹어플리케이션에서 : /hello를 하면 이 '메소드'를 실행함
    //model : mvc의 model
    @GetMapping("hello")
    public String hello(Model model){
        //spring 직접 받기
        model.addAttribute("data", "spring!!");
        //return 값 : templates : hello.html로 return 함
        return "1.hello";
    }

    //크롬 입력 : http://localhost:8080/Hello-mvc?name=spring!
    //웹어플리케이션에서 : /Hello-mvc를 하면 이 '메소드'를 실행함
    //@RequestParam : 외부에서 파라미터 받아올 때 사용
    @GetMapping("Hello-mvc")
    public String helloMvc(@RequestParam(value="name") String name, Model model){
        //파라미터로 받아온 name을 넘겨줌 : 키. 값
        model.addAttribute("name", name);
        return "2.hello-template";
    }

    //API
    //크롬 입력 : http://localhost:8080/hello-string?name=spring!!!
    //@ResponseBody :http의 헤더가 아닌 바디에 다음 내용을 직접 넣어주겠다는 의미
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        //요청한 문자 그대로 내려감
        return "hello " + name; //"hello String"
    }

    //API : 객체 만들기. Json 방식
    //@ResponseBody의 기본 : Json
    //크롬 검색 : http://localhost:8080/hello-api?name=spring
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        //객체를 넘김
        return hello;
    }

    //static class 객체
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}