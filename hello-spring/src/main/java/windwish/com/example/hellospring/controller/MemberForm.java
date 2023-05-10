package windwish.com.example.hellospring.controller;

public class MemberForm {

    //resources/templates/members/createMemberForm의 name과 매칭
    private String name;

    //getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
