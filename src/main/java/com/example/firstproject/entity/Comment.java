package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id // id 삽입!
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 id 넘버링 + db가 id를 자동으로 생성해줌
    private Long id;

    @ManyToOne // 여러 개의 코멘트가 하나의 게시글에 달린다. (댓글 엔티티 여러 개가 하나의 Article 에 연관됨)
    @JoinColumn(name = "article_id") // 테이블에서 연결된 대상의 정보! 그니까 외래키의 이름
    private Article article;

    @Column // 이 어노테이션이 있어야 DB 에서 컬럼으로 인식 가능
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 발생
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id 가 없어야 합니다.");
        if (dto.getArticleId() != article.getId()) // 주소 id 랑 json 의 id 가 다를 때
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id 가 잘못되었습니다.");

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id 가 입력되었습니다.");

        // 객체를 갱신
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();

        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}
