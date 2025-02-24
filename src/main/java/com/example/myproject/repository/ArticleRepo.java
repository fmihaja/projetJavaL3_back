package com.example.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myproject.model.Article;

public interface ArticleRepo extends JpaRepository<Article, Long> {
}