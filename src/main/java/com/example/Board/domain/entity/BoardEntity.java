package com.example.Board.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "board")
@DynamicInsert // @DynamicInsert : insert 시 null 인 필드 제외
public class BoardEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;                       // PK

    String title;                  // 제목

    String content;                // 내용

    String writer;                 // 작성자

    int viewCnt;                   // 조회 수

    Boolean noticeYn;              // 공지글 여부

//    @ColumnDefault("'0'")
    /**
     * 그럼 @ColumnDefault의 역할은 뭐야?
     * 다 해보고 나니 생긴 의문은 이거였다. 내가 익히 알아 온 @ColumnDefault의 역할은 언제 발휘되는 건지?
     * 근데 레퍼런스를 읽어보니 create문을 자동 생성해줄 때 그 역할이 적용되는 것 같다. none 일때도 그 역할이 적용 되는가? 어디서 확인 하지?
     * 그래서 해 봤다. 새 프로젝트를 파고 application.yml의 내용을 jpa.hibernate.ddl-auto : create-drop으로 설정해서 서버 구동 시 table이 있으면 전부 drop하고 새로 create ddl을 생성하게 했다.
     */
    Boolean deleteYn;              // 삭제 여부

    @CreationTimestamp
    LocalDateTime createdDate;     // 생성일시

    LocalDateTime modifiedDate;    // 최종 수정일시
}
