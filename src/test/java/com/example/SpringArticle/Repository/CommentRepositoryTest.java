package com.example.SpringArticle.Repository;

import com.example.SpringArticle.Entity.Article;
import com.example.SpringArticle.Entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("SELECT FROM article_id")
    void findByArticleId() {
        Long articleId = 1L;
        Article article = new Article(1L, "AAAA", "1111");
        Comment comment = new Comment(1L, article, "Alex", "AAAA??");
        List<Comment> expected = Arrays.asList(comment);
        List<Comment> actual = commentRepository.findByArticleId(articleId);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void findByNickname() {
        String nickname = "Alex";
        Comment comment = new Comment(1L, new Article(1L, "AAAA", "1111"), "Alex", "AAAA??");
        List<Comment> expected = Arrays.asList(comment);
        List<Comment> actual = commentRepository.findByNickname(nickname);
        assertEquals(expected.toString(), actual.toString());
    }
}