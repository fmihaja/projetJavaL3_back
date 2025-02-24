package com.example.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myproject.model.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {
}