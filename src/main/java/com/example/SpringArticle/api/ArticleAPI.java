package com.example.SpringArticle.api;

import com.example.SpringArticle.Entity.Article;
import com.example.SpringArticle.Repository.ArticleRepository;
import com.example.SpringArticle.Service.ArticleService;
import com.example.SpringArticle.dto.ArticleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleAPI {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/api/articles")
    public List<Article> articles(){
        return articleService.articles();
    }

    @GetMapping("/api/articles/{id}")
    public Article detail(@PathVariable Long id){
        return articleService.detail(id);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> Create(@RequestBody ArticleForm articleForm){
        Article created = articleService.create(articleForm);
        return (created != null) ? ResponseEntity.status(HttpStatus.OK).body(created) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> Update(@PathVariable Long id, @RequestBody ArticleForm articleForm){
        Article updated = articleService.update(id, articleForm);
        return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> Delete(@PathVariable Long id){
        Article deleted = articleService.Delete(id);
        return (deleted != null) ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

