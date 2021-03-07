package com.example.salessystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.salessystem.exception.ResourceNotFoundException;
import com.example.salessystem.model.Client;
import com.example.salessystem.repository.ClientRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/apic")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<Client> getAllclients() {
        return clientRepository.findAll();
    }

    @PostMapping("/clients")
    public Client createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping("/clients/{id}")
    public Client getClientById(@PathVariable(value = "id") Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
    }

    @PutMapping("/clients/{id}")
    public Client updateClient(@PathVariable(value = "id") Long clientId,
                                           @Valid @RequestBody Client clientDetails) {

        Client Client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));

        Client.setLastName(clientDetails.getLastName());
        Client.setName(clientDetails.getName());
        Client.setMobile(clientDetails.getMobile());
        Client updatedClient = clientRepository.save(Client);
        return updatedClient;
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long clientId) {
        Client Client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));

        clientRepository.delete(Client);

        return ResponseEntity.ok().build();
    }
}
