package windwish.com.example.hellospring.domain;

import javax.persistence.*;

//@Entity : jap가 관리해준다는 의미
@Entity
public class Member {

    //1.아이디 식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //2.이름
    @Column(name = "username")
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
