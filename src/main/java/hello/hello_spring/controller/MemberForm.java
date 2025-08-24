package hello.hello_spring.controller;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MemberForm {
    @NotBlank(message = "이름은 필수 입력입니다.")
    @Size(max=10, message = "이름은 최대 10자입니다.")
    private String name;
    @NotBlank(message = "비밀번호은 필수 입력입니다.")
    @Size(min=7, max=15, message = "비밀번호는 최소 7자, 최대 15자 입니다.")
    private String pw;

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
