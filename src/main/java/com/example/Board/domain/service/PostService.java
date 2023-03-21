package com.example.Board.domain.service;

import com.example.Board.domain.entity.BoardEntity;
import com.example.Board.domain.post.PostMapper;
import com.example.Board.domain.post.PostRequest;
import com.example.Board.domain.post.PostResponse;
import com.example.Board.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;
    private final BoardRepository boardRepository;

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     * @return Generated PK
     */
    @Transactional
    public Long savePost(final PostRequest params) {
        postMapper.save(params);
        return params.getId();
    }

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    public PostResponse findPostById(final Long id) {
        return postMapper.findById(id);
    }

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return PK
     */
    @Transactional
    public Long updatePost(final PostRequest params) {
        postMapper.update(params);
        return params.getId();
    }

    /**
     * 게시글 삭제
     * @param id - PK
     * @return PK
     */
    public Long deletePost(final Long id) {
        postMapper.deleteById(id);
        return id;
    }

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    public List<PostResponse> findAllPost() {
        return postMapper.findAll();
    }

    // jpa 게시글 등록
    @Transactional
    public Long save(BoardEntity boardEntity) {
        boardRepository.save(boardEntity);
        return boardEntity.getId();
    }

    // jpa 게시글 조회
    public Optional<BoardEntity> findOne(Long boardId) {
        return boardRepository.findById(boardId);
    }

    // jpa 게시글 삭제
    public Long deleteById(Long boardId) {
        boardRepository.deleteById(boardId);
        return boardId;
    }

    public void findAll() {
        boardRepository.findAll();
    }

    /**
     * @Service
     * PostMapper 인터페이스의 @Mapper와 유사하며,
     * 해당 클래스가 비즈니스 로직을 담당하는 Service Layer의 클래스임을 의미합니다.
     *
     * @RequiredArgsConstructor
     * 과거에는 일반적으로 @Autowired 또는 @Inject 어노테이션을 이용하여 빈(Bean)을 주입받고는 했었습니다.
     * 스프링은 생성자로 빈(Bean)을 주입하는 방식을 권장한다고 하는데요.
     * 해당 어노테이션은 롬복(Lombok)에서 제공해주는 기능으로,
     * 클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 만들어주는 역할을 합니다.
     *
     * private final PostMapper postMapper;
     * public PostService(PostMapper postMapper) {
     *     this.postMapper = postMapper;
     * }
     *
     * postMapper
     * 스프링 컨테이너에 등록된 PostMapper 인터페이스입니다.
     * 게시글 CRUD를 처리해주기 위해서는 당연히 빈(Bean)을 주입받아야겠죠?
     *
     * @Transactional
     * 스프링에서 제공해주는 여러 가지 트랜잭션 처리 방법 중 하나로, 선언적 트랜잭션으로 불리는 기능입니다.
     * 해당 어노테이션이 선언된 메서드가 호출되면 트랜잭션이 시작되고,
     * 메서드의 정상 종료 여부에 따라 Commit 또는 Rollback 됩니다.
     * 해당 어노테이션에는 여러 가지 옵션이 있습니다.(별도 검색 필요)
     *
     */

}
