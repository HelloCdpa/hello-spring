package com.hello.hellospring.controller;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//스프링이 알게 해야 관리됨 @Controller @Service..라 해야 스프링이 컨테이너에 등록해줌
// 스프링은 스프링 빈을 등록할 때 기본으로 싱글톤으로 등록한다. (하나만 등록)
@Controller
public class MemberController {
    private final MemberService memberService;

    //스프링이 넣어줌
    //생성자 주입, 그 외에 setter, 필드주입 으로 의존성을 주입할 수 있다.
    //생성자 주입 권장
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }
    @GetMapping("/members")
    public String findAll(Model model){
        List<Member> memberList = memberService.findAll();
        model.addAttribute("memberList",memberList);
        return "/members/memberList";
    }

}
