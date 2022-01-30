package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA 와 연동한 테스트!
class CommentRepositoryTest {


    @Autowired CommentRepository commentRepository; // CommentRepository 클래스를 불러온다. @로 객체 + new 까지 한번에

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회") // 테스트 결과에 보여줄 이름
    void findByArticleId() {
        /*Case 1: 4번 게시글의 모든 댓글 조회*/
        {
            // 입력 데이터 준비
            Long articleId = 4L;

            // 실제 수행
            // commentRepository 가 findByArticleId 를 통해서 comment 를 리턴함. 근데 comment 는 Comment(List 묶음) 타입임
            List<Comment> comments =  commentRepository.findByArticleId(articleId);
//            List<Comment> comments =  commentRepository.findByArticleId( (@Param("articleId") Long articleId);


            // 예상하기
            Article article = new Article(4L,"당신의 인생 영화는?","댓글1");
            Comment a = new Comment(1L,article,"park","영화1");
            Comment b = new Comment(2L,article,"Kim","영화2");
            Comment c = new Comment(3L,article,"Choi","영화3");

            List <Comment> expected = Arrays.asList(a,b,c);

            // 검증
            assertEquals(expected.toString(), comments.toString(),"4번 글의 모든 댓글 출력!");
        }
    }

    @Test
    void findByNickname() {
    }
}