package com.example.myproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myproject.model.User;
import com.example.myproject.repository.UserRepo;

@Service
public class UserServ {
    @Autowired
    private UserRepo userRepo;

    public User createUser(User user) {
        // Exemple de logique métier
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email déjà utilisé !");
        }
        return userRepo.save(user);
    }

    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public Optional<User> findUser(Long id){
        return userRepo.findById(id);
    }
    
}
