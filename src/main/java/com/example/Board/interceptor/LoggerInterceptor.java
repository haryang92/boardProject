package com.example.Board.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("===============================================");
        log.debug("==================== BEGIN ====================");
        log.debug("Request URI ===> " + request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("==================== END ======================");
        log.debug("===============================================");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}

/**
 * 1. 인터셉터(Interceptor)란?
 * 인터셉터는 이름 그대로 "무언가를 가로챈다"라는 의미를 가집니다.
 * 인터셉터는 컨트롤러의 URI에 접근하는 과정에서 무언가를 제어할 필요가 있을 때 사용됩니다.
 * 정확히는 컨트롤러에 접근하기 전과 후로 나뉘는데요.
 * 예를 들어, 회원제로 이루어지는 시스템이 있다고 가정했을 때
 * 로그인이나 계정의 권한과 관련된 처리 등을 인터셉터를 이용해서 더욱 효율적으로 처리할 수 있습니다.
 *
 * 스프링에서 인터셉터는 HandlerInterceptor 인터페이스를 implements 하여 구현할 수 있는데요.
 * 해당 인터페이스는 preHandle, postHandle, afterCompletion 총 세 개의 추상 메서드를 포함하고 있다.
 *
 * @Slf4j
 * 롬복(Lombok)에서 제공해주는 어노테이션으로, 로깅 추상화 라이브러리입니다.
 * 로깅 추상화란, 로깅을 직접 하지 않고 로깅 구현체를 찾아 기능을 사용할 수 있게 해주는 것을 의미합니다.
 *
 * preHandle( )
 * 컨트롤러의 메서드에 매핑된 특정 URI가 호출됐을 때 실행되는 메서드로,
 * 컨트롤러를 경유(접근)하기 직전에 실행되는 메서드입니다.
 *
 * 우리는 사용자가 어떠한 기능을 수행했는지를 파악하기 위하여
 * 해당 기능과 매핑된 URI 정보가 콘솔에 로그가 출력되도록 처리합니다.
 *
 * postHandle( )
 * 컨트롤러를 경유(접근) 한 후, 즉 화면(View)으로 결과를 전달하기 전에 실행되는 메서드입니다.
 * preHandle( )과는 반대로 요청(Request)의 끝을 알리는 로그가 콘솔에 출력되도록 처리합니다.
 */