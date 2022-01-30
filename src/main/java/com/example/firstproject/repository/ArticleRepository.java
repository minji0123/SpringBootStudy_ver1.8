package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {  //CRUD 를 제공해주는 Repository 를 상속받음

    @Override
    ArrayList<Article> findAll();
}
