package com.example.myproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.model.StockMovement;
import com.example.myproject.service.StockMovementServ;

@RestController
@RequestMapping("/api/stockmovements")
public class StockMovementController {

    @Autowired
    private StockMovementServ stockMovementService;

    @GetMapping
    public List<StockMovement> getAllStockMovements() {
        return stockMovementService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMovement> getStockMovementById(@PathVariable Long id) {
        Optional<StockMovement> stockMovement = stockMovementService.findById(id);
        if (stockMovement.isPresent()) {
            return ResponseEntity.ok(stockMovement.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public StockMovement createStockMovement(@RequestBody StockMovement stockMovement) {
        return stockMovementService.save(stockMovement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockMovement> updateStockMovement(@PathVariable Long id, @RequestBody StockMovement stockMovementDetails) {
        Optional<StockMovement> stockMovement = stockMovementService.findById(id);
        if (stockMovement.isPresent()) {
            StockMovement updatedStockMovement = stockMovement.get();
            updatedStockMovement.setDate(stockMovementDetails.getDate());
            updatedStockMovement.setType(stockMovementDetails.getType());
            updatedStockMovement.setQuantite(stockMovementDetails.getQuantite());
            updatedStockMovement.setProduit(stockMovementDetails.getProduit());
            return ResponseEntity.ok(stockMovementService.save(updatedStockMovement));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockMovement(@PathVariable Long id) {
        if (stockMovementService.findById(id).isPresent()) {
            stockMovementService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}