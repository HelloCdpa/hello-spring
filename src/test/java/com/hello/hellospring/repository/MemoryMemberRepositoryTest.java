package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //메서드가 실행될 때마다 실행되는 함수
    //테스트는 의존관계가 없이 실행되어야 함 하나의 메서드가 진행된 후 데이터를 지우기
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // 방법 1
        // Assertions.assertEquals(result, member);
        // 방법 2 static import 하기
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // shift + f6
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        repository.findByName("spring1").get();

        Member result = repository.findById(member1.getId()).get();

        assertThat(result).isEqualTo(member1);

    }

    //순서에 의존적으로 설계 X
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }


}
