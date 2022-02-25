package com.example.SpringArticle.Controller;

import com.example.SpringArticle.Repository.ArticleRepository;
import com.example.SpringArticle.Entity.Article;
import com.example.SpringArticle.Service.CommentService;
import com.example.SpringArticle.dto.ArticleForm;
import com.example.SpringArticle.dto.CommentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/main")
    public String main() {
        return "Article/main";
    }

    @GetMapping("/articles")
    public String Articles(Model model) {
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList", articleList);
        return "Article/articles";
    }

    @GetMapping("/articles/new")
    public String NewArticleForm() {
        return "Article/new";
    }

    @PostMapping("/articles/create")
    public String Create(ArticleForm articleForm) {
        log.info(articleForm.toString());
        Article article = articleForm.toEntity();
        log.info(article.toString());
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String Detail(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", article);

        List<CommentDto> commentDtos = commentService.comments(id);
        model.addAttribute("commentDtos", commentDtos);
        return "Article/detail";
    }

    @GetMapping("/articles/{id}/delete")
    public String Delete(@PathVariable Long id, RedirectAttributes rttr) {
        Article Target = articleRepository.findById(id).orElse(null);
        if (Target != null) {
            articleRepository.delete(Target);
            rttr.addFlashAttribute("msg", "삭제완료");
        }
        return "redirect:/articles";
    }

    @GetMapping("articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", article);
        return "Article/edit";
    }

    @PostMapping("/articles/update")
    public String Update(ArticleForm articleForm) {
        Article article = articleForm.toEntity();
        Article Target = articleRepository.findById(article.getId()).orElse(null);
        if (Target != null) {
            articleRepository.save(article);
        }
        return "redirect:/articles";
    }
}