package com.hello.hellospring;

import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import com.hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    // 스프링 빈에 직접 등록하는 법(자바)
    // 정형화 되지 않거나 상황에 따라 구현 클래스를 변경해야하면 설정을 통해 스프링 빈으로 등록한다.
    @Bean
    public MemberService memberService(){
        //memberService 가 memberRepository 를 사용하도록
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}
