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
import com.example.myproject.model.Client;
import com.example.myproject.service.ClientServ;


@RestController
@RequestMapping("/api/clients/")
public class ClientController {
    @Autowired
    private ClientServ clientService;

    @PostMapping
    public ResponseEntity<ApiResponse<Client>> createClient(@RequestBody Client client) {
        Client savedUser = clientService.createClient(client);
        ApiResponse<Client> response=new ApiResponse<>("client crée.", savedUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<Client>>> getAllClient() {
        List<Client> clients= clientService.getAllClient();
        ApiResponse<List<Client>> response=new ApiResponse<>("liste des clients", clients);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{email}")
    public ResponseEntity<ApiResponse<Optional<Client>>>  findClient(@PathVariable String email){
        Optional<Client> client= clientService.findClient(email);
        if (client.isPresent()) {
            ApiResponse<Optional<Client>> response = new ApiResponse<>("Client found", client);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Optional<Client>> response = new ApiResponse<>("Client not found", Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Client>> updateClient(@RequestBody Client client) {
        Client updatedClient = clientService.updateClient(client);
        if (updatedClient != null) {
            ApiResponse<Client> response = new ApiResponse<>("Client mis à jour.", updatedClient);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Client> response = new ApiResponse<>("Client non trouvé.", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{email}")
    public ResponseEntity<ApiResponse<Void>> deleteClient(@PathVariable String email) {
        boolean isDeleted = clientService.deleteClient(email);
        if (isDeleted) {
            ApiResponse<Void> response = new ApiResponse<>("Client supprimé.", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Void> response = new ApiResponse<>("Client non trouvé.", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
