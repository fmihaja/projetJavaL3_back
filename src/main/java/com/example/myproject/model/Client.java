package com.example.myproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; // Import de l'annotation Lombok



@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class Client {

    @Id
    @Column(nullable= false, length=50)
    private String email;

    @Column(nullable = false, length=100)
    private String name;

    @Column(nullable = true)
    private String firstName;
}
