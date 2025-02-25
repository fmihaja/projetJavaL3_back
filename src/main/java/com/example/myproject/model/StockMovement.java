package com.example.myproject.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor; // Import de l'annotation Lombok
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock_movements")

@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private LocalDate date;

    @Column(nullable = false, length=3)
    private String type;

    @Column(columnDefinition="integer default 0")
    private Integer quantite;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Product produit;

}
