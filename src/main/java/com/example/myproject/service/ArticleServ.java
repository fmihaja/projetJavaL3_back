package com.example.myproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myproject.model.Article;
import com.example.myproject.repository.ArticleRepo;

@Service
public class ArticleServ {

    @Autowired
    private ArticleRepo articleRepo;

    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepo.findById(id).orElse(null);
    }

    public Article saveArticle(Article article) {
        return articleRepo.save(article);
    }

    public Article updateArticle(Article article) {
        if (articleRepo.existsById(article.getArticleId())) {
            return articleRepo.save(article);
        } else {
            return null;
        }
    }

    public boolean deleteArticle(Long id) {
        if (articleRepo.existsById(id)) {
            articleRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}