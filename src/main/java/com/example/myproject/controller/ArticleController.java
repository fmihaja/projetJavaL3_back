package com.example.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.dto.ApiResponse;
import com.example.myproject.model.Article;
import com.example.myproject.service.ArticleServ;

@RestController
@RequestMapping("/api/articles/")
public class ArticleController {
    @Autowired
    private ArticleServ articleService;

    @PostMapping
    public ResponseEntity<ApiResponse<Article>> createArticle(@RequestBody Article article) {
        Article savedArticle = articleService.saveArticle(article);
        ApiResponse<Article> response = new ApiResponse<>("Article crée.", savedArticle);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Article>>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        ApiResponse<List<Article>> response = new ApiResponse<>("liste des articles", articles);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Article>> findArticle(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        if (article != null) {
            ApiResponse<Article> response = new ApiResponse<>("Article found", article);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Article> response = new ApiResponse<>("Article not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // @PutMapping
    // public ResponseEntity<ApiResponse<Article>> updateArticle(@RequestBody Article article) {
    //     Article updatedArticle = articleService.updateArticle(article);
    //     if (updatedArticle != null) {
    //         ApiResponse<Article> response = new ApiResponse<>("Article mis à jour.", updatedArticle);
    //         return new ResponseEntity<>(response, HttpStatus.OK);
    //     } else {
    //         ApiResponse<Article> response = new ApiResponse<>("Article non trouvé.", null);
    //         return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    //     }
    // }

    // @DeleteMapping("{id}")
    // public ResponseEntity<ApiResponse<Void>> deleteArticle(@PathVariable Long id) {
    //     boolean isDeleted = articleService.deleteArticle(id);
    //     if (isDeleted) {
    //         ApiResponse<Void> response = new ApiResponse<>("Article supprimé.", null);
    //         return new ResponseEntity<>(response, HttpStatus.OK);
    //     } else {
    //         ApiResponse<Void> response = new ApiResponse<>("Article non trouvé.", null);
    //         return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    //     }
    // }
}