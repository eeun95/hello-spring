package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    // 스프링 컨테이너에 딱 하나만 등록해주면 스프링 빈에 등록되어있는 멤버 서비스를 연결해줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
