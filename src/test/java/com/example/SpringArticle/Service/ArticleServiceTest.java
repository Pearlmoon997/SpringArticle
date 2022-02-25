package com.example.SpringArticle.Service;

import com.example.SpringArticle.Entity.Article;
import com.example.SpringArticle.dto.ArticleForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void articles() {
        Article a = new Article(1L, "AAAA", "1111");
        Article b = new Article(2L, "BBBB", "3333");
        Article c = new Article(3L, "CCCC", "5555");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        List<Article> articles = articleService.articles();
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void detail_Success() {
        Long id = 1L;
        Article expected = new Article(1L, "AAAA", "1111");
        Article actual = articleService.detail(id);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void detail_Failed_noID() {
        Long id = -1L;
        Article expected = null;
        Article actual = articleService.detail(id);
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void create_Success_TitleAndContent() {
        String title = "GGGG";
        String content = "8888";
        ArticleForm articleForm = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        Article actual = articleService.create(articleForm);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void create_Failed_ID() {
        String title = "HHHH";
        String content = "9999";
        ArticleForm articleForm = new ArticleForm(4L, title, content);
        Article expected = null;
        Article actual = articleService.create(articleForm);
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void update_Success() {
        Long id = 1L;
        String title = "RRRR";
        String content = "1111";
        ArticleForm articleForm = new ArticleForm(1L, title, content);
        Article updated = articleService.update(id, articleForm);
        Article expected = articleService.detail(1L);
        Article actual = articleService.update(1L, articleForm);
        assertEquals(expected.toString(), actual.toString());
    }


    @Test
    @Transactional
    void update_Failed_noID() {
        Long id = -1L;
        String title = "RRRR";
        String content = "1111";
        ArticleForm articleForm = new ArticleForm(id, title, content);
        Article expected = articleService.detail(id);
        Article actual = articleService.update(id, articleForm);
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void delete_Success() {
        Long id = 1L;
        Article deleted = articleService.Delete(id);
        Article expected = articleService.detail(id);
        Article actual = articleService.Delete(id);
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void delete_Failed_noID() {
        Long id = -1L;
        Article deleted = articleService.Delete(id);
        Article expected = articleService.detail(id);
        Article actual = articleService.detail(id);
        assertEquals(expected, actual);
    }
}