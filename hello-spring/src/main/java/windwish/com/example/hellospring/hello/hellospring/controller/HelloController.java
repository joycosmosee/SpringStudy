package windwish.com.example.hellospring.hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    //웹어플리케이션에서 : /hello를 하면 이 메소드를 실행함
    //model : mvc의 model
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "spring!!");
        return "hello";
    }
}
