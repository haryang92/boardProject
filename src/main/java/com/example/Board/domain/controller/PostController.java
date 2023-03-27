package com.example.Board.domain.controller;

import com.example.Board.domain.dto.MessageDTO;
import com.example.Board.domain.post.PostRequest;
import com.example.Board.domain.post.PostResponse;
import com.example.Board.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성 페이지
//    @GetMapping(value = "post/write.do")
//    public String postWrite(Model model) {
//        return "post/write";
//    }

//    @GetMapping("/post/write.do")
//    public String openPostWrite(Model model) {
//        String title = "제목";
//        String content = "내용";
//        String writer = "tester";
//
//        model.addAttribute("t", title);
//        model.addAttribute("c", content);
//        model.addAttribute("w", writer);
//
//        return "post/write";
//    }

    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDTO params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }

    // 게시글 작성 페이지
    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
        if (id != null) {
            PostResponse post = postService.findPostById(id);
            model.addAttribute("post", post);
        }
        return "post/write";
    }

    // 신규 게시글 생성
    @PostMapping("/post/save.do")
    public String savePost(final PostRequest params, Model model) {
        postService.savePost(params);
//        return "redirect:/post/list.do";
        MessageDTO message = new MessageDTO("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 게시글 리스트 페이지
    @GetMapping("/post/list.do")
    public String openPostList(Model model) {
        List<PostResponse> posts = postService.findAllPost();
        model.addAttribute("posts", posts);
        return "post/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/post/view.do")
    public String openPostView(@RequestParam final Long id, Model model) {
        PostResponse post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "post/view";
    }

    // 기존 게시글 수정
    @PostMapping("/post/update.do")
    public String updatePost(final PostRequest params, Model model) {
        postService.updatePost(params);
//        return "redirect:/post/list.do";
        MessageDTO message = new MessageDTO("게시글 수정이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 게시글 삭제
    @PostMapping("/post/delete.do")
    public String deletePost(@RequestParam final Long id, Model model) {
        postService.deletePost(id);
//        return "redirect:/post/list.do";
        MessageDTO message = new MessageDTO("게시글 삭제가 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 1-10까지
    public void test() {
        List<Integer> list = new ArrayList<>();
        for(int i= 1; i<=10; i++) {
            list.add(i);
        }

        List<Integer> list2 = IntStream.of(1,2,3,4,5).boxed().toList();

        List<Integer> list3 = IntStream.range(0,10).boxed().toList();


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
     *
     * model.addAttribute( )
     * 해당 메서드를 이용해서 화면(HTML)으로 데이터를 전달할 수 있습니다.
     * 메서드의 인자로는 이름(String name), 값(Object value)을 전달하는데요,
     * 웬만해서는 이름(name)과 값(value)을 동일하게 지정합니다.
     * HTML에서는 ${ } 표현식을 이용해서 전달받은 데이터에 접근할 수 있습니다.
     *
     *@RequestParam
     * 뷰(화면)에서 보낸 파라미터를 전달받는 데 사용됩니다.
     * 예를 들어, 신규 게시글을 등록하는 경우에 게시글 번호(id)는 null로 전송됩니다.
     * 하지만, 기존 게시글을 수정하는 경우에는 컨트롤러로 게시글 번호(idx)가 파라미터로 전송되고,
     * 전달받은 게시글 번호(id)를 이용하여 게시글을 조회하여 뷰로 전달합니다.
     * 새로운 게시글을 등록하는 경우에는 게시글 번호(idx)가 필요하지 않기 때문에
     * required 속성을 false로 지정합니다.
     * 필수(required) 속성은 default 값이 true이며, required 속성을 false로 지정하지 않으면
     * id를 파라미터로 전달받지 못했을 때 예외가 발생합니다.
     *
     * 전체 로직
     * 게시글 번호(id)를 파라미터로 전달받은 경우, 즉 기존 게시글을 수정하는 경우에는
     * id를 이용하여 조회한 게시글 응답 객체를 post라는 이름으로 뷰(View)로 전달합니다.
     *
     * params
     *폼 데이터를 컨트롤러 메서드의 파라미터로 전송합니다.
     * PostRequest의 멤버 변수명과 사용자 입력 필드의 "name" 값이 동일하면
     * PostRequest 타입의 객체인 params의 각 멤버 변수에 "name" 값을 통해 전달된 value가 매핑됩니다
     *
     */
}
