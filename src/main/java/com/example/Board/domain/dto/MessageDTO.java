package com.example.Board.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Getter
@AllArgsConstructor
public class MessageDTO {

    private String message;                // 사용자에게 전달할 메시지
    private String redirectUri;            // 리다이렉트 URI
    private RequestMethod method;          // HTTP 요청 메서드
    private Map<String, Object> data;      // 화면(View)으로 전달할 데이터(파라미터)

}

/*
@AllArgsConstructor
서비스 영역의 @RequiredArgsConstructor와 마찬가지로 롬복(Lombok)에서 제공해주는 기능입니다.
해당 어노테이션이 선언된 클래스에는 전체 멤버 변수를 필요로 하는 생성자가 생성됩니다.
 */
