package com.example.myproject.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @Column(nullable = false, length = 50)
    private String articleId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int prix;

    @ManyToMany(mappedBy = "articles")
    private List<Order> orders;
}