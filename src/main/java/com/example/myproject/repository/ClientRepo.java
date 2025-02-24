package com.example.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myproject.model.Client;
// import com.example.myproject.model.User;
@Repository
public interface  ClientRepo  extends JpaRepository<Client, String> {
    // Optional<Client> findByEmail(String email);
    // boolean existsByEmail(String email);
}
