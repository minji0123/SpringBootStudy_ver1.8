package com.example.firstproject.dto;
//DTO 클래스!
// 이 파일은 form 데이터를 받아올 그릇임

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // constructor 롬복
@ToString // toString 롬복
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id,title,content); // 새롭게 Article을 만들어서 return 해준다.
        // 안에 들어갈 변수들은 넣어줘야함(constructor 로 정의된 애들)
    }

}
