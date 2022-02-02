package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // REST 컨드롤러는 GET 요청 시 JSON, DATA 를 반환한다.
public class CommentApiController {
    @Autowired // @는 RestController 가 service 와 함께 협업할 수 있게 해준다. springboot 가 미리 생성해놓은 객체를 가져다가 자동 연결해줌 DI(Dependency Injection)
    private CommentService commentService;

// 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        // 1. 서비스에게 위임해서 리스트를 가져온다.
        List<CommentDto> dtos= commentService.comments(articleId);

        // 2. 결과 응답
       return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


// 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto){ // @가 json 데이터를 받게 해줌. 안하면 인식을 못해서 null
        // 1. 서비스에게 위임해서 값을 가져온다.
        CommentDto createDto = commentService.create(articleId, dto);

        // 2. 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

// 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto){ // @가 json 데이터를 받게 해줌. 안하면 인식을 못해서 null
        // 1. 서비스에게 위임해서 값을 가져온다.
        CommentDto updateDto = commentService.update(id, dto);

        // 2. 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

// 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        // 1. 서비스에게 위임해서 값을 가져온다.
        CommentDto updateDto = commentService.delete(id);

        // 2. 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }
}
