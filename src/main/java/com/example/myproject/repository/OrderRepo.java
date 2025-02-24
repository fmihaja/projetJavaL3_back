package com.example.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myproject.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, String> {
}