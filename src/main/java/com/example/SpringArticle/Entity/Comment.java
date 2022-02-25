package com.example.SpringArticle.Entity;

import com.example.SpringArticle.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패, 없는 ID");
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패, 잘못된 ID");

        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        if(this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패, 잘못된 ID");
        if(dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if(dto.getBody() != null)
            this.body = dto.getBody();
    }
}
