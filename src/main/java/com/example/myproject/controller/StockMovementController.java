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

import com.example.myproject.dto.ApiResponse;
import com.example.myproject.model.StockMovement;
import com.example.myproject.service.StockMovementServ;

@RestController
@RequestMapping("/api/stockmovements")
public class StockMovementController {

    @Autowired
    private StockMovementServ stockMovementService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StockMovement>>> getAllStockMovements() {
        List<StockMovement> stockMovements = stockMovementService.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Stock movements retrieved successfully", stockMovements));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StockMovement>> getStockMovementById(@PathVariable Long id) {
        Optional<StockMovement> stockMovement = stockMovementService.findById(id);
        if (stockMovement.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>("Stock movement retrieved successfully", stockMovement.get()));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("Stock movement not found", null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StockMovement>> createStockMovement(@RequestBody StockMovement stockMovement) {
        StockMovement savedStockMovement = stockMovementService.save(stockMovement);
        return ResponseEntity.status(201).body(new ApiResponse<>("Stock movement created successfully", savedStockMovement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StockMovement>> updateStockMovement(@PathVariable Long id, @RequestBody StockMovement stockMovementDetails) {
        Optional<StockMovement> stockMovement = stockMovementService.findById(id);
        if (stockMovement.isPresent()) {
            StockMovement updatedStockMovement = stockMovement.get();
            updatedStockMovement.setDate(stockMovementDetails.getDate());
            updatedStockMovement.setType(stockMovementDetails.getType());
            updatedStockMovement.setQuantite(stockMovementDetails.getQuantite());
            updatedStockMovement.setProduit(stockMovementDetails.getProduit());
            return ResponseEntity.ok(new ApiResponse<>("Stock movement updated successfully", stockMovementService.save(updatedStockMovement)));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("Stock movement not found", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStockMovement(@PathVariable Long id) {
        if (stockMovementService.findById(id).isPresent()) {
            stockMovementService.deleteById(id);
            return ResponseEntity.status(204).body(new ApiResponse<>("Stock movement deleted successfully", null));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("Stock movement not found", null));
        }
    }
}