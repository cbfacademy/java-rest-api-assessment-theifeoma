package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.services.ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client-details")
public class ClientDtoController {

    private final ClientDetailsService clientDetailsService;

    @Autowired
    public ClientDtoController(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @GetMapping("/all-clients")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        try {
            List<ClientDto> clients = clientDetailsService.getAllDto();
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addNewClient(@RequestBody ClientDto client) {
        try {
            clientDetailsService.addNewClient(client);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
        try {
            clientDetailsService.deleteClientD(clientId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<String> updateClientEmail(@PathVariable Long clientId,
                                                    @RequestParam String newClientEmail) {
        try {
            String result = clientDetailsService.updateClientEmail(clientId, newClientEmail);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get/{clientId}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long clientId) {
        try {
            ClientDto client = clientDetailsService.getClientById(clientId);
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
