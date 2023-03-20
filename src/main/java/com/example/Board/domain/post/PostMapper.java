package com.example.Board.domain.post;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     */
    void save(PostRequest params);

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    PostResponse findById(Long id);

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     */
    void update(PostRequest params);

    /**
     * 게시글 삭제
     * @param id - PK
     */
    void deleteById(Long id);

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    List<PostResponse> findAll();

    /**
     * 게시글 수 카운팅
     * @return 게시글 수
     */
    int count();


    /**
     * @Mapper
     *
     * 기존의 스프링은 DAO(Data Access Object) 클래스에 @Repository 어노테이션을 선언해서 해당 클래스가
     * DB와 통신하는 클래스임을 명시하고는 했습니다. MyBatis는 Mapper 인터페이스(이하 Mapper)와
     * XML Mapper를 통해 DB와 통신하는데요. Mapper는 우리가 알고 있는 Java의 인터페이스(Interface)이고,
     * XML Mapper는 실제로 DB에 접근해서 호출할 SQL 쿼리를 작성(선언)하는 XML 파일입니다.
     *
     * 여기서 꼭 기억해 주셔야 할 게 있는데요. Mapper에는 @Mapper 어노테이션을 필수적으로 선언해 주셔야
     * 하며, Mapper와 XML Mapper는 XML Mapper의 namespace라는 속성을 통해 연결된다는 점입니다.
     * 연결하는 방법은 뒤에서 예제를 통해 알아보도록 하겠습니다.
     */
}
