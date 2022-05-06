package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 각 테스트를 실행하기 전 DI
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void join() {
        // given 상황이 주어짐 - when 실행했을 때 - then 결과가 이렇게 나와야함

        //given
        Member member = new Member();
        member.setName("spring1");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        // alt enter
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    //저장될 때 중복 검사 예외를 검증하는 것도 중요
    @Test
    void duplication(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");


        Member member2 = new Member();
        member2.setName("spring1");


        // when
        memberService.join(member1);
        /* 방법 1 try catch 사용
        try{
            memberService.join(member1);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
        //방법 2
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");






    }


    @Test
    void findAll() {
    }


    @Test
    void findOne(){
        Member member = new Member();
        member.setName("spring1");
        memberService.join(member);
        Optional<Member> memberTest = memberService.findOne(member.getId());
        assertThat(member.getName()).isEqualTo(memberTest.get().getName());
    }
}
