package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    //테스트 클래스 빠르게 만들기 : ctrl + shift + t
    private final MemberRepository memberRepository;

    // 외부에서 넣어주도록 스프링 컨테이너에 있는 MemberService 를 주입해줌
    // 생성하는 시점에만 넣고 그 이후에 못만지도록 막아버릴 수 있다.
    // 스프링에 올라가는 것들만 @Autowired 가 적용된다.
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
//        //같은 이름이 있는 중복회원 X
//        Optional<Member> result = memberRepository.findByName((member.getName()));
//        // ifPresent 값이 있으면 동작
//        result.ifPresent(m ->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        //Optional 반환하지 않고 바로 사용하는 것을 권장
        // 메서드 뽑아내기 : ctrl alt shift t / ctrl alt m
        validateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName((member.getName()))
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }
    // 전체 회원 조회
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }



}
