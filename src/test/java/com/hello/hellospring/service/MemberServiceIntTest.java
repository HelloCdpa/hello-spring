package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//통합테스트
//스프링 컨테이너와 테스트를 함께 실행한다.
@SpringBootTest
//테스트는 반복할 수 있어야 한다. rollback 의 역할
// db에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
// test 에 붙어있을 경우에만
@Transactional
public class MemberServiceIntTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

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
