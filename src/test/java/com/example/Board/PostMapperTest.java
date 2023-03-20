package com.example.Board;

import com.example.Board.domain.post.PostMapper;
import com.example.Board.domain.post.PostRequest;
import com.example.Board.domain.post.PostResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostMapperTest {

    @Autowired
    PostMapper postMapper;

    @Test
    void save() {
        PostRequest params = new PostRequest();
        params.setTitle("8번 게시글 제목");
        params.setContent("8번 게시글 내용");
        params.setWriter("테스터");
        params.setNoticeYn(false);
        postMapper.save(params);

        List<PostResponse> posts = postMapper.findAll();
        System.out.println("전체 게시글 개수는 : " + posts.size() + "개입니다.");
    }

    /*
    1. postMapper
    @Autowired를 이용해서 스프링 컨테이너에 등록된 PostMapper 빈(Bean)을 클래스에 주입합니다.

    2. save( )
    게시글을 생성하는 save( ) 메서드입니다. PostRequest 객체를 생성하고, set( ) 메서드를 이용해서 값을 세팅
    한 후 PostMapper 인터페이스의 save( ) 메서드를 호출합니다. 메서드가 호출되면 PostMapper.xml의 save 쿼리가 실행되는데요. #{ } 표현식을 통해 PostRequest 객체의 멤버 변수에 접근하게 됩니다.

    테이블의 PK인 id는 auto_increment에 의해 자동 증가되므로 INSERT 쿼리에서는 생략해도 되지만,
    SQL 조각인 "postColumns"와 인클루드(Include)를 활용하기 위해 VALUES에 id를 넣어주었습니다.
    */

    @Test
    void findById() {
        PostResponse post = postMapper.findById(2L);
        try {
            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
            System.out.println(postJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    /*
    3. post 객체
    PostMapper 인터페이스의 findById( ) 메서드 실행 결과입니다. 앞에서 생성된 게시글의 PK인 1을 인자로
    전달해서 게시글 상세정보를 조회합니다.

    4. postJson
    스프링 부트에 기본으로 내장되어 있는 Jackson 라이브러리를 이용해서, 조회한 1번 게시글의 응답 객체를
    JSON 문자열로 변환한 결과입니다. 객체는 디버깅을 해보지 않는 이상 확인이 까다롭기에 JSON 문자열로
    변경해서 콘솔에 출력해 보았습니다.
     */

    @Test
    void update() {
        // 1. 게시글 수정
        PostRequest params = new PostRequest();
        params.setId(2L);
        params.setTitle("1번 게시글 제목 수정합니다.");
        params.setContent("1번 게시글 내용 수정합니다.");
        params.setWriter("haryang");
        params.setNoticeYn(true);
        postMapper.update(params);

        // 2. 게시글 상세정보 조회
        PostResponse post = postMapper.findById(1L);
        try {
            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
            System.out.println(postJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    3. update( )
    사실, 게시글 수정은 생성과 마찬가지로 테이블에 데이터를 저장하는 개념입니다. 단지, 없었던 데이터를
    생성하는 것인지, 기존 데이터를 수정하는 것인지의 차이만을 가집니다. 이 미묘한 차이는 PK인 id를 통해
    구분할 수 있는데요, 테이블에 새로 생성되는 게시글은 auto_increment에 의해 자동 생성되지만, 게시글을
    수정하기 위해서는 수정할 게시글의 PK인 id를 파라미터로 전달해 주어야 합니다.


    만약 WHERE 조건 없이 UPDATE 쿼리가 실행된다면 모든 데이터가 동일한 값으로 UPDATE 되어버릴 테고,
    실무에서 이런 일이 발생한다면 정말 커다란 사고가 됩니다.
     */

    @Test
    void delete() {
        System.out.println("삭제 이전의 전체 게시글 개수는 : " + postMapper.findAll().size() + "개입니다.");
        postMapper.deleteById(1L);
        System.out.println("삭제 이후의 전체 게시글 개수는 : " + postMapper.findAll().size() + "개입니다.");
    }

}
