package com.example.myproject.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMovementDTO {
    private Long id;
    private LocalDate date;
    private String type;
    private Integer quantite;
}