package com.example.myproject.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepo.findById(id);
    }

    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public Supplier updateSupplier(Supplier supplier) {
        if (supplierRepo.existsById(supplier.getSupplierId())) {
            return supplierRepo.save(supplier);
        } else {
            return null;
        }
    }

    public boolean deleteSupplier(Long id) {
        if (supplierRepo.existsById(id)) {
            supplierRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}