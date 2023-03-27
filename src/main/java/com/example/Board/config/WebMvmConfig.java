package com.example.Board.config;

import com.example.Board.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvmConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor())
                .excludePathPatterns("/css/**", "/images/**", "/js/**");
    }

}

/**
 * addPathPatterns( ), excludePathPatterns( ) 메서드를 이용해서 특정 패턴의 주소(URI)를 추가 또는 제외할 수 있는데요.
 *
 * WebMvcConfigurer 인터페이스
 * 해당 인터페이스를 구현하면 @EnableWebMvc의 자동 설정을 베이스로 가져가며,
 * 개발자가 원하는 설정까지 추가할 수 있다는 장점이 있습니다. (Override 가능)
 *
 * addInterceptors( )
 * 애플리케이션 내에 인터셉터를 등록해주는 기능을 합니다.
 * InterceptorRegistry의 addInterceptor( ) 메서드를 이용하여 인터셉터 클래스를 등록하는데요.
 * excludePathPatterns( )는 "여기에 지정된 URI나 경로(Path)는 인터셉터 호출에서 제외하겠어!"
 * 라고 이해하면 된다. 즉, 정적(static) 파일을 무시(ignore)하겠다는 의미인 것이다.
 *
 * excludePathPatterns( )가 인터셉터 호출에서 경로를 제외한다면,
 * addPathPatterns( )는 인터셉터 호출에서 경로를 추가(허용)한다는 의미로 이해해 주시면 되겠습니다.
 */