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
import com.example.myproject.model.Order;
import com.example.myproject.service.OrderServ;

@RestController
@RequestMapping("/api/orders/")
public class OrderController {
    @Autowired
    private OrderServ orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.save(order);
        ApiResponse<Order> response = new ApiResponse<>("Order created.", savedOrder);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
        List<Order> orders = orderService.findAll();
        ApiResponse<List<Order>> response = new ApiResponse<>("List of orders", orders);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Optional<Order>>> findOrder(@PathVariable String id) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            ApiResponse<Optional<Order>> response = new ApiResponse<>("Order found", order);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Optional<Order>> response = new ApiResponse<>("Order not found", Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Order>> updateOrder(@RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(order);
        if (updatedOrder != null) {
            ApiResponse<Order> response = new ApiResponse<>("Order updated.", updatedOrder);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Order> response = new ApiResponse<>("Order not found.", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable String id) {
        boolean isDeleted = orderService.deleteOrder(id);
        if (isDeleted) {
            ApiResponse<Void> response = new ApiResponse<>("Order deleted.", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Void> response = new ApiResponse<>("Order not found.", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}