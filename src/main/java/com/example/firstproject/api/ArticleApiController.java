package com.example.firstproject.api;


import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // REST 컨드롤러는 GET 요청 시 JSON, DATA 를 반환한다.
@Slf4j // 로깅을 위한 어노테이션 (log 찍을 수 있게)
public class ArticleApiController {

    @Autowired // springboot 가 미리 생성해놓은 객체를 가져다가 자동 연결해줌 DI(Dependency Injection)
    private ArticleService articleService;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index(); // 이제 service 를 통해서 데이터를 받아오게 함
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create (@RequestBody ArticleForm dto){ // @가 json 데이터를 받게 해줌. 안하면 인식을 못해서 null
        Article created = articleService.create(dto);
        return (created != null)
                ? ResponseEntity.status(HttpStatus.OK).body(created)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        Article updated = articleService.update(id,dto);
        return (updated != null)
                ? ResponseEntity.status(HttpStatus.OK).body(updated)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public  ResponseEntity<Article> delete(@PathVariable Long id){ // @가 json 데이터를 받게 해줌. 안하면 인식을 못해서 null

        // 요리는 요리사한테 시키고 웨이터는 주문만 받으셈
        Article deleted = articleService.delete(id);

        // 데이터 반환
        return (deleted !=null)
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 트랜젝션이 -> 실패한다면 -> 롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) { // @가 json 데이터를 받게 해줌. 안하면 인식을 못해서 null
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null)
                ? ResponseEntity.status(HttpStatus.OK).body(createdList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
