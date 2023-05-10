package windwish.com.example.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    //슬러시 - 로컬 호스트 8080하면 이게 호출
    @GetMapping("/")
    public String home(){
        return "3.home";
    }
}
