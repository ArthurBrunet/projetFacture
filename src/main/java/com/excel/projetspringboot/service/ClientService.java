package com.excel.projetspringboot.service;

import com.excel.projetspringboot.models.Client;
import com.excel.projetspringboot.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAll() {
        return (List<Client>) clientRepository.findAll();
    }

    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> getByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

}
