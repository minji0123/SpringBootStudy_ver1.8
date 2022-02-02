package com.example.firstproject.dto;


import com.example.firstproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    @JsonProperty("article_id") // json 과 주소의 id 변수이름이 달라서 인식 못하기 때문에 인식해줄 수 있게 처리
    private Long articleId;
    private String nickname;
    private String body;

    // static 은 class 메소드 안에 선언되는거다.
    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(), //id 를 받아오고
                comment.getArticle().getId(), // article 의 id 를 받아오고
                comment.getNickname(), // 닉네임 받아오고
                comment.getBody() // 내용 받아오고
        );
    }
}
