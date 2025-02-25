package com.example.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myproject.model.StockMovement;

@Repository
public interface StockMovementRepo extends JpaRepository<StockMovement, Long> {
}