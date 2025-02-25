package com.example.myproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class ArticleDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
}