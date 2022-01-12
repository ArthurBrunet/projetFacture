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

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> clientList() {
        return (List<Client>) clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

}
