//package com.cbfacademy.apiassessment.controllers;
//
//import com.cbfacademy.apiassessment.entities.ClientDetails;
//import com.cbfacademy.apiassessment.services.ClientDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/clients")
//public class ClientDetailsController {
//
//    private final ClientDetailsService clientDetailsService;
//
//    @Autowired
//    public ClientDetailsController(ClientDetailsService clientDetailsService) {
//        this.clientDetailsService = clientDetailsService;
//    }
//
//    @GetMapping("/all")
//    public List<ClientDetails> getAllClients() throws IOException {
//        return clientDetailsService.getAllClients();
//    }
//
//    @PostMapping("/add")
//    public void addNewClient(@RequestBody ClientDetails client) throws IOException {
//        clientDetailsService.addNewClient(client);
//    }
//
//    @DeleteMapping("/delete/{clientId}")
//    public void deleteClient(@PathVariable Long clientId) throws IOException {
//        clientDetailsService.deleteClient(clientId);
//    }
//
//    @GetMapping("/sort/{role}")
//    public List<ClientDetails> sortClientsByRole(@PathVariable String role) throws IOException {
//        return clientDetailsService.sortAllClientsByRole(role);
//    }
//
//    @PutMapping("/update/{clientId}")
//    public void updateClient(@PathVariable Long clientId, @RequestBody ClientDetails updatedClient) throws IOException {
//        clientDetailsService.updateClient(clientId, updatedClient);
//    }
//}
//
