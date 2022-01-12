package com.excel.projetspringboot.controller;

import com.excel.projetspringboot.models.Client;
import com.excel.projetspringboot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<List<Client>>(clientService.clientList(),HttpStatus.OK);
    }

    @PostMapping("/client")
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) {
        Client clientDb = clientService.createClient(client);
        return new ResponseEntity<Client>(clientDb,HttpStatus.CREATED);
    }
}
