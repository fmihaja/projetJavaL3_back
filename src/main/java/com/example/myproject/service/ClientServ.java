package com.example.myproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myproject.dto.ArticleDTO;
import com.example.myproject.dto.ClientDTO;
import com.example.myproject.dto.OrderDTO;
import com.example.myproject.model.Article;
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

    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClient() {
        List<Client> clients = clientRepo.findAll();
        return clients.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
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

    private ClientDTO convertToDTO(Client client) {
        List<OrderDTO> orders = client.getOrders().stream()
                .map(order -> new OrderDTO(order.getOrderId(), order.getOrderDate(), convertArticlesToDTO(order.getArticles())))
                .collect(Collectors.toList());
        return new ClientDTO(client.getEmail(), client.getName(), client.getFirstName(), orders);
    }

    private List<ArticleDTO> convertArticlesToDTO(List<Article> articles) {
        return articles.stream()
                .map(article -> new ArticleDTO(article.getArticleId(), article.getName(), article.getPrice().doubleValue(), article.getQuantity()))
                .collect(Collectors.toList());
    }
}