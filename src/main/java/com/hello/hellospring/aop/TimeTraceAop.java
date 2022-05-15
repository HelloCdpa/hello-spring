package com.hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
//bean 에 등록해서 사용하거나 Component scan 사용
@Component
public class TimeTraceAop {
    // 공통관심사 타겟팅
    // 패키지 하위에 다 적용
    // 보통 패키지 레벨로 적용
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try{
            //다음 메서드로 진행
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs =  finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }





    }

}
