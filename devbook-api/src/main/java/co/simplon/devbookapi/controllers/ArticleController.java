package co.simplon.devbookapi.controllers;

import co.simplon.devbookapi.dtos.ArticleCreate;
import co.simplon.devbookapi.services.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/article")
@CrossOrigin("*")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> postArticle(@Valid @RequestBody ArticleCreate input) throws IOException {
        return articleService.postArticle(input);
    }

}
