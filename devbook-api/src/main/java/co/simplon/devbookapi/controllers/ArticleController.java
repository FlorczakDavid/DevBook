package co.simplon.devbookapi.controllers;

import co.simplon.devbookapi.dtos.ArticleCreate;
import co.simplon.devbookapi.services.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postArticle(@Valid @RequestBody ArticleCreate input, Principal principal) throws IOException {
        String username = principal.getName();
        articleService.postArticle(input, username);
    }

}
