package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.services.ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public List<ClientDto> getAllClients() {
        return clientDetailsService.getAllDto();
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewClient(@RequestBody ClientDto client){
        clientDetailsService.addNewClient(client);
    }

    @DeleteMapping("/delete/{clientId}")
    public void deleteClient(@PathVariable Long clientId) {
        clientDetailsService.deleteClientD(clientId);
    }


    @PutMapping("/update/{clientId}")
    public String updateClientEmail(@PathVariable Long clientId,
                                    @RequestParam String newClientEmail) {
        return clientDetailsService.updateClientEmail(clientId, newClientEmail);
    }
}
