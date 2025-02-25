package com.example.myproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myproject.model.StockMovement;
import com.example.myproject.repository.StockMovementRepo;

@Service
public class StockMovementServ {

    @Autowired
    private StockMovementRepo stockMovementRepository;

    public List<StockMovement> findAll() {
        return stockMovementRepository.findAll();
    }

    public Optional<StockMovement> findById(Long id) {
        return stockMovementRepository.findById(id);
    }

    public StockMovement save(StockMovement stockMovement) {
        return stockMovementRepository.save(stockMovement);
    }

    public void deleteById(Long id) {
        stockMovementRepository.deleteById(id);
    }
}