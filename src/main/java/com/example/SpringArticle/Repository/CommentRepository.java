package com.example.SpringArticle.Repository;

import com.example.SpringArticle.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 게시글 id 조회
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    @Query(value = "SELECT * FROM comment WHERE nickname = :nickname", nativeQuery = true)
    List<Comment> findByNickname(String nickname);
}
