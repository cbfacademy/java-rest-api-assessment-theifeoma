package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.entities.ClientDetails;
import com.cbfacademy.apiassessment.services.ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/clientdetails")
public class ClientDetailsController {

    private final ClientDetailsService clientDetailsService;

    @Autowired
    ClientDetailsController(ClientDetailsService clientDetailsService){
        this.clientDetailsService = clientDetailsService;
    }

    @GetMapping
    public List<ClientDetails> getAllClients(){
        return clientDetailsService.getAllClients();
    }
    @PostMapping
    public void registerNewClient(@RequestBody ClientDetails clientDetails){
        clientDetailsService.addNewClient(clientDetails);
    }

    @DeleteMapping(path = "{clientId}")
    public void deleteClient(@PathVariable ("clientId") Long clientId){
        clientDetailsService.deleteClient(clientId);
    }

    @GetMapping(path = "{role}")
    public List<ClientDetails> sortClientsByRole(@PathVariable ("role") String role){
        return clientDetailsService.sortAllClientsByRole(role);
    }
}
