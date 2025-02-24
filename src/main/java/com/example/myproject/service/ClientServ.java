package com.example.myproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myproject.dto.ClientDTO;
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
        return null; // ou lancez une exception si le client n'existe pas
    }

    public List<ClientDTO> getAllClient() {
        List<Client> clients = clientRepo.findAll();
        return clients.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private ClientDTO convertToDTO(Client client) {
        // Ne touche pas à client.getOrders()
        return new ClientDTO(client.getEmail(), client.getName(), client.getFirstName());
    }

    public Optional<ClientDTO> findClient(String email) {
        Optional<Client> client = clientRepo.findById(email);
        return client.map(this::convertToDTO);
    }

    public boolean deleteClient(String email) {
        if (clientRepo.existsById(email)) {
            clientRepo.deleteById(email);
            return true;
        }
        return false;
    }
}