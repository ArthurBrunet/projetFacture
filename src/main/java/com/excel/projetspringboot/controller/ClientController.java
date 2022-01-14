package com.excel.projetspringboot.controller;

import com.excel.projetspringboot.models.Client;
import com.excel.projetspringboot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public String getAllClients(ModelMap modelMap) {
        List<Client> clientList = clientService.getAll();
        modelMap.addAttribute("clientList", clientList);
        return "client/clients";
    }

    @GetMapping("/client")
    public String getClient(@RequestParam(name = "id") Long id, ModelMap modelMap) {
        Optional<Client> clientOptional = clientService.getById(id);
        clientOptional.ifPresent(client -> modelMap.addAttribute("client", client));
        return "client/clientDetails";
    }

    @GetMapping("/client/form")
    public String formClient(ModelMap modelMap) {
        modelMap.addAttribute("client", new Client());
        return "client/clientForm";
    }

    @PostMapping(value = {"/client/create"})
    public ResponseEntity<Client> createClient(@ModelAttribute Client client) {
        Client clientDb = clientService.create(client);
        return new ResponseEntity<>(clientDb,HttpStatus.CREATED);
    }
}
