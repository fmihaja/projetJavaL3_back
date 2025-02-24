package com.example.myproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myproject.model.Client;
import com.example.myproject.repository.ClientRepo;

@Service
public class ClientServ {
    @Autowired
    private ClientRepo clientRepo;

    public Client createClient(Client client) {
        return clientRepo.save(client);
    }

    public Client updateClient(Client client) {
        if (clientRepo.existsById(client.getEmail())) {
            return clientRepo.save(client);
        }
        return null; // or throw an exception if the client does not exist
    }

    public List<Client> getAllClient(){
        return clientRepo.findAll();
    }

    public Optional<Client> findClient(String email){
        return clientRepo.findById(email);
    }

    public boolean deleteClient(String email){
        if (clientRepo.existsById(email)) {
            clientRepo.deleteById(email);
            return true; 
        }
        return false;
    }
    
}
