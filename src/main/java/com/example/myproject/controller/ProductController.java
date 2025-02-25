package com.example.myproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.dto.ApiResponse;
import com.example.myproject.dto.ProductDTO;
import com.example.myproject.dto.StockMovementDTO;
import com.example.myproject.model.Product;
import com.example.myproject.model.StockMovement;
import com.example.myproject.service.ProductServ;

@RestController
@RequestMapping("/api/products/")
public class ProductController {

    @Autowired
    private ProductServ productService;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
        List<Product> products = productService.findAll();
        List<ProductDTO> productDTOs = products.stream()
                                               .map(this::toProductDTO)
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>("Products retrieved successfully", productDTOs));
    }

    @GetMapping("{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<ProductDTO>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ProductDTO productDTO = toProductDTO(product.get());
            return ResponseEntity.ok(new ApiResponse<>("Product retrieved successfully", productDTO));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("Product not found", null));
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ApiResponse<ProductDTO>> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        ProductDTO productDTO = toProductDTO(savedProduct);
        return ResponseEntity.status(201).body(new ApiResponse<>("Product created successfully", productDTO));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            Product updatedProduct = product.get();
            updatedProduct.setName(productDetails.getName());
            updatedProduct.setPrice(productDetails.getPrice());
            updatedProduct.setQuantite(productDetails.getQuantite());
            ProductDTO productDTO = toProductDTO(productService.save(updatedProduct));
            return ResponseEntity.ok(new ApiResponse<>("Product updated successfully", productDTO));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("Product not found", null));
        }
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        if (productService.findById(id).isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.status(204).body(new ApiResponse<>("Product deleted successfully", null));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("Product not found", null));
        }
    }


    private ProductDTO toProductDTO(Product product) {
    // Ensure stockMovements is not null
    List<StockMovement> stockMovements = product.getStockMovements();
    if (stockMovements == null) {
        stockMovements = new ArrayList<>();
    }

    // Force lazy loading of stockMovements
    stockMovements.size();
    
    List<StockMovementDTO> stockMovementDTOs = stockMovements.stream()
            .map(this::toStockMovementDTO)
            .collect(Collectors.toList());
    return new ProductDTO(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getQuantite(),
        stockMovementDTOs
    );
}

    private StockMovementDTO toStockMovementDTO(StockMovement stockMovement) {
        return new StockMovementDTO(
            stockMovement.getId(),
            stockMovement.getDate(),
            stockMovement.getType(),
            stockMovement.getQuantite()
        );
    }
}