package com.example.Board.domain.controller;

import com.example.Board.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성 페이지
    @GetMapping(value = "post/write.do")
    public String postWrite(Model model) {
        return "post/write";
    }

    /**
     * @Controller
     * 해당 클래스가 사용자의 요청과 응답을 처리하는, 즉 UI를 담당하는 컨트롤러 클래스임을 의미합니다.
     *
     * @GetMapping
     * 이전 버전의 스프링에서 컨트롤러 메서드에 URI와 HTTP 요청 메서드를 매핑하려면,
     *
     * @RequestMapping을 이용해서 value 속성에는 URI 값을,
     * method 속성에는 HTTP 요청 메서드를 지정하는 방식을 사용하였습니다.
     * 스프링 4.3 버전부터는 @GetMapping, @PostMapping 등
     * 요청 메서드의 타입별로 매핑을 처리할 수 있는 어노테이션이 추가되었습니다.
     *
     * 기존의 URI 매핑) @RequestMapping(value = "...", method = RequestMethod.XXX)
     * 새로운 URI 매핑) @xxxMapping("...")
     *
     * 리턴 타입
     * 컨트롤러 메서드의 리턴 타입은 void, String, ModelAndView, Map, List 등
     * 여러 가지 타입을 리턴 타입으로 지정할 수 있습니다.
     * 대표적으로 String과 ModelAndView는 사용자에게 보여줄 화면(HTML 경로)을
     * 리턴 문에 지정해서 처리하는데요. 예전에는 ModelAndView가 주로 사용되었지만
     * 최근에는 String을 많이 선호한다고 합니다.
     * 리턴 문에 선언된 HTML 경로의 끝에는, 접미사(suffix)로 확장자(".html")가 자동으로 연결되기 때문에
     * 확장자를 생략할 수 있습니다.
     *
     * Model
     * 메서드의 파라미터로 선언된 Model 인터페이스는 데이터를 뷰(HTML)로 전달하는 데 사용됩니다.
     * 화면(HTML)을 처리하는 과정에서 자세히 알아보도록 하겠습니다.
     */
}
