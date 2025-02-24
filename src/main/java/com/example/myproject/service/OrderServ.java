package com.example.myproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myproject.model.Order;
import com.example.myproject.repository.OrderRepo;

@Service
public class OrderServ {

    @Autowired
    private OrderRepo orderRepository;

    // @Autowired
    // private ClientRepo clientRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(String orderId) {
        return orderRepository.findById(orderId);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void deleteById(String orderId) {
        orderRepository.deleteById(orderId);
    }

}