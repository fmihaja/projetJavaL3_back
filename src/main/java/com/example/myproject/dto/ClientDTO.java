package com.example.myproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor

public class ClientDTO {
    private String email;
    private String name;
    private String firstName;
    // Constructeurs, Getters, Setters
}