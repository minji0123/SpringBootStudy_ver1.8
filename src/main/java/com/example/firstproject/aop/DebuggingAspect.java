package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // AOP 클래스 선언 : 부가 기능을 주입하는 클래스!
@Component // IOC 컨테이너가 해당 객체를 생성 및 관리하도록 해줌
@Slf4j // log
public class DebuggingAspect {

    // 대상 메소드 선택: CommentService#create() 에다가 넣을거임
    @Pointcut("execution(* com.example.firstproject.service.CommentService.create(..))")
    private void cut() {}


    // 실행 시점 설정 (부가기능을 언제 실행하도록 하겠다? cut() 메소드가 실행되기 전에 하겠다!
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) { //JoinPoint 는 기본제공파라미터, cut()의 대상 메소드
        // 입력값 가져오기
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        // 입력값 로깅하기
        // CommentService#create() 의 입력값 => 5 ...
        // CommentService#create() 의 입력값 => CommentDto(id= ....
        for (Object obj : args) { // 향상된 for 문(foreach)
            log.info("{}#{}의 입력값 -> {}", className, methodName, obj);
        }
    }


    // 실행 시점 설정 (부가기능을 언제 실행하도록 하겠다? cut() 메소드가 실행된 후에 하겠다!
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, //JoinPoint 는 기본제공파라미터, cut()의 대상 메소드
                                   Object returnObj) { // returnObj 는 리턴값
        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        // 반환값 로깅
        // CommentService#create() 의 반환값 => CommentDto(id= ....
        log.info("{}#{}의 반환값 -> {}", className, methodName, returnObj);

    }
}
