package com.example.SpringArticle.Service;

import com.example.SpringArticle.Entity.Article;
import com.example.SpringArticle.Repository.ArticleRepository;
import com.example.SpringArticle.dto.ArticleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> articles() {
        return articleRepository.findAll();
    }

    public Article detail(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Transactional
    public Article create(ArticleForm articleForm) {
        Article article = articleForm.toEntity();
        if(article.getId() != null)
            return null;
        return articleRepository.save(article);
    }

    @Transactional
    public Article update(Long id, ArticleForm articleForm) {
        Article article = articleForm.toEntity();
        Article Target = articleRepository.findById(id).orElse(null);
        if(Target == null || id != article.getId()){
            return null;
        }
        Target.patch(article);
        Article updated = articleRepository.save(Target);
        return updated;
    }

    @Transactional
    public Article Delete(Long id) {
        Article Target = articleRepository.findById(id).orElse(null);
        if(Target == null){
            return null;
        }
        articleRepository.delete(Target);
        return Target;
    }
}
