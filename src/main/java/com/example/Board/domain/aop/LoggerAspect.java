package com.example.Board.domain.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Slf4j
@Aspect
@Component
public class LoggerAspect {
    @Around("execution(* com.example.Board..*Controller.*(..)) || execution(* com.example.Board..*Service.*(..)) || execution(* com.example.Board..*Mapper.*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String name = joinPoint.getSignature().getDeclaringTypeName();
        String type =
                StringUtils.contains(name, "Controller") ? "Controller ===> " :
                        StringUtils.contains(name, "Service") ? "Service ===> " :
                                StringUtils.contains(name, "Mapper") ? "Mapper ===> " : "";

        log.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
        return joinPoint.proceed();
    }
}

/**
 * @Component
 * 스프링 컨테이너에 빈(Bean)으로 등록하기 위한 어노테이션입니다.
 * @Bean은 개발자가 제어할 수 없는 외부 라이브러리를 빈(Bean)으로 등록할 때 사용하고,
 * @Component는 개발자가 직접 정의한 클래스를 빈(Bean)으로 등록할 때 사용합니다.
 *
 * @Aspect
 * AOP 기능을 하는 클래스의 클래스 레벨에 지정하는 어노테이션입니다.
 *
 * @Around
 * 어드바이스(Advice)의 종류 중 한 가지로 어드바이스는 모두 다섯 가지의 타입이 있습니다.
 * 다섯 가지 중 어라운드(Around)는 메서드의 호출 자체를 제어할 수 있기 때문에
 * 어드바이스 중 가장 강력한 기능이라고 볼 수 있습니다.
 *
 * @Before (type : Before Advice) - Target 메서드 호출 이전에 적용할 어드바이스 정의
 * @AfterReturning (type : After returning) - Target 메서드가 성공적으로 실행되고, 결괏값을 반환한 뒤에 적용
 * @AfterThrowing (type : After throwing) - Target 메서드에서 예외 발생 이후에 적용((try/catch 문의 catch와 유사))
 * @After (type : After) - Target 메서드에서 예외 발생에 관계없이 적용((try/catch 문의 finally와 유사))
 * @Around (type : Around) - Target 메서드 호출 이전과 이후 모두 적용 (가장 광범위하게 사용됨)
 *
 * execution
 * @Around 안에서 execution으로 시작하는 구문은 포인트컷을 지정하는 문법으로,
 * execution을 제외한 명시자로는 within과 bean이 있습니다.
 * 가장 많이 사용되는 명시자는 execution으로, 접근 제어자, 리턴 타입, 타입 패턴, 메서드,
 * 파라미터 타입, 예외 타입 등을 조합해서 정교한 포인트컷을 만들 수 있습니다.
 *
 * execution(PostResponse select*(..))
 * - 리턴 타입이 PostResponse 타입이고, 메서드의 이름이 select로 시작하며, 파라미터가 0개 이상인 모든 메서드가 호출될 때 (0개 이상은 패키지, 메서드, 파라미터 등 모든 것을 의미)
 *
 * execution(* com.study.controller.*())
 * - 해당 패키지 밑에 파라미터가 없는 모든 메서드가 호출될 때
 *
 * execution(* com.study.controller.*(..))
 * - 해당 패키지 밑에 파라미터가 0개 이상인 모든 메서드가 호출될 때
 *
 * execution(* com.study..select(*))
 * - com.study 패키지의 모든 하위 패키지에 존재하는 select로 시작하고, 파라미터가 한 개인 모든 메서드가 호출될 때
 *
 * execution(* com.study..select(*, *))
 * - com.study 패키지의 모든 하위 패키지에 존재하는 select로 시작하고, 파라미터가 두 개인 모든 메서드가 호출될 때
 *
 */

