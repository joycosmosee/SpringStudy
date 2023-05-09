package windwish.com.example.hellospring.controller.domain;

public class Member {

    //1.아이디 식별자
    private Long id;
    //2.이름
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
