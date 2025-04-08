package co.simplon.devbookapi.controllers;

import co.simplon.devbookapi.dtos.ArticleCreate;
import co.simplon.devbookapi.dtos.ArticleView;
import co.simplon.devbookapi.services.ArticleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ArticleView getArticle(@Valid @RequestBody ArticleCreate input) throws IOException {
        return articleService.getGraph(input);
    }
}
