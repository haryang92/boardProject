package com.example.Board.service;

import com.example.Board.domain.entity.BoardEntity;
import com.example.Board.domain.post.PostRequest;
import com.example.Board.domain.post.PostResponse;
import com.example.Board.domain.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    void save() {
        PostRequest params = new PostRequest();
        params.setTitle("1번 게시글 제목");
        params.setContent("1번 게시글 내용");
        params.setWriter("테스터");
        params.setNoticeYn(false);
        postService.savePost(params);
    }

    @Test
    void saveJpa() {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle("9번 게시글 제목");
        boardEntity.setContent("9번 게시글 내용");
        boardEntity.setWriter("tester");
        boardEntity.setNoticeYn(false);
        postService.save(boardEntity);
    }

    @Test
    void findByIdJpa() {
        Optional<BoardEntity> boardEntity = postService.findOne(3L);
        System.out.println(boardEntity.get().getContent());
        // optional 객체 내용을 확인하고 싶으면 어떻게 해야하는 가?
    }

    @Test
    void deleteJpa() {
//        System.out.println("삭제 이전의 전체 게시글 개수는 : " + postService.findAll().size() + "개입니다.");
        postService.deleteById(1L);
//        System.out.println("삭제 이후의 전체 게시글 개수는 : " + postService.findAll().size() + "개입니다.");
    }

}
