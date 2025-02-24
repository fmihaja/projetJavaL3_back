package com.example.myproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myproject.model.Supplier;
import com.example.myproject.repository.SupplierRepo;

@Service
public class SupplierServ {

    @Autowired
    private SupplierRepo supplierRepo;

    public List<Supplier> getAllSuppliers() {
        return supplierRepo.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepo.findById(id).orElse(null);
    }

    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public void deleteSupplier(Long id) {
        supplierRepo.deleteById(id);
    }
}