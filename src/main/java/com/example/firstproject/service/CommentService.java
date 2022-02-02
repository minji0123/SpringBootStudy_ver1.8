package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 선언! 요리사임 ( 서비스 객체를 스프링부트에 생성)
public class CommentService {

    @Autowired // service 는 repository 와 협업해야 한다. springboot 가 미리 생성해놓은 객체를 가져다가 자동 연결해줌 DI(Dependency Injection)
    private CommentRepository commentRepository; // 요리사의 보조들
    @Autowired
    private ArticleRepository articleRepository; // 요리사의 보조들


    public List<CommentDto> comments(Long articleId) {
        // 조회 : 댓글 목록
        // articleId 에 담겨진 모든 comments 들을 담는다. list 형태로!
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//
//        // 변환 : 엔티티 -> dto (왜냐면!! //List<CommentDto> dtos// 엔티티를 이케 리스트로 dto형태로 담았기 때문
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//
//        for (int i = 0; i < comments.size(); i++){
//            Comment c = comments.get(i); // 일단 엔티티 list를 풀고
//            CommentDto dto = CommentDto.createCommentDto(c);  // dto 형태로 바꿔줌
//            dtos.add(dto); // list 형태의 comments(엔티티)들을 하나하나씩 꺼내서! add 해준다.
//        }
//
//        // 반환
//        return dtos;

        // 위의 모든 코드를 stream 으로!
        return commentRepository.findByArticleId(articleId)
                .stream() // 스트림화해주고
                .map(comment -> CommentDto.createCommentDto(comment)) // map 으로 하나하나 꺼내옴
                .collect((Collectors.toList())); // stream 으로 반환하는거를 list 로 묶어줌

    }


    @Transactional // 중간에 문제가 생가면 롤백!
    public CommentDto create(Long articleId, CommentDto dto) {
        log.info("입력값 => {}", articleId);
        log.info("입력값 => {}", dto);

        // 게시글 조회 및 예외 발생
        // articleRepository 를 통해서 게시글을 가져와서 article 에 담는다. -> 왜냐면 게시물이 있어야 댓글이 어디에 달릴 지 알 수 있기 때문에
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시물이 없습니다."));


        // 댓글 엔티티 생성
        // Comment 클래스 에서, dto(json 형태)와, article 를 넣어서 comment 를 만들었음
        Comment comment = Comment.createComment(dto, article);


        // 댓글 엔티티를 db 로 저장
        Comment created = commentRepository.save(comment);

        // Comment -> dto 로 변경하여 변환
//        return CommentDto.createCommentDto(created);
        CommentDto createdDto = CommentDto.createCommentDto(created);
        log.info("반환값 -> {}", createdDto);
        return createdDto;

    }

    @Transactional // 중간에 문제가 생가면 롤백!
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));

        // 댓글 수정
        target.patch(dto); // json 형태로 넘어온 dto 데이터를 수정할거시야

        // 수정된 댓글을 잘 저장한다. (db 로 갱신)
        Comment upeated = commentRepository.save(target);

        // 댓글 엔티티를 dto 로 변환 및 반환
        return CommentDto.createCommentDto(upeated);
    }

    @Transactional // 중간에 문제가 생가면 롤백!
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));

        // 댓글을 db 에서 삭제
        commentRepository.delete(target);

        // 삭제 댓글을 dto 로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }
}
