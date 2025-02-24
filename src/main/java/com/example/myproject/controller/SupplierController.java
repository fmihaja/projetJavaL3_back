package com.example.myproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.myproject.model.Supplier;
import com.example.myproject.service.SupplierServ;

@RestController
@RequestMapping("/api/suppliers/")
public class SupplierController {
    @Autowired
    private SupplierServ supplierService;

    @PostMapping
    public ResponseEntity<ApiResponse<Supplier>> createSupplier(@RequestBody Supplier supplier) {
        Supplier savedSupplier = supplierService.saveSupplier(supplier);
        ApiResponse<Supplier> response = new ApiResponse<>("Supplier created.", savedSupplier);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Supplier>>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        ApiResponse<List<Supplier>> response = new ApiResponse<>("List of suppliers", suppliers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Optional<Supplier>>> findSupplier(@PathVariable Long id) {
        Optional<Supplier> supplier = supplierService.getSupplierById(id);
        if (supplier.isPresent()) {
            ApiResponse<Optional<Supplier>> response = new ApiResponse<>("Supplier found", supplier);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Optional<Supplier>> response = new ApiResponse<>("Supplier not found", Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Supplier>> updateSupplier(@RequestBody Supplier supplier) {
        Supplier updatedSupplier = supplierService.updateSupplier(supplier);
        if (updatedSupplier != null) {
            ApiResponse<Supplier> response = new ApiResponse<>("Supplier updated.", updatedSupplier);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Supplier> response = new ApiResponse<>("Supplier not found.", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSupplier(@PathVariable Long id) {
        boolean isDeleted = supplierService.deleteSupplier(id);
        if (isDeleted) {
            ApiResponse<Void> response = new ApiResponse<>("Supplier deleted.", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Void> response = new ApiResponse<>("Supplier not found.", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}