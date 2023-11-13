package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.services.ClientTradeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/client-trade-details")
public class ClientTradeDetailsController {
    private final ClientTradeDetailsService clientTradeDetailsService;

    @Autowired
    public ClientTradeDetailsController(ClientTradeDetailsService clientTradeDetailsService) {
        this.clientTradeDetailsService = clientTradeDetailsService;
    }

    @GetMapping("/all-clients")
    public List<ClientTradeDetails> getAllClients() throws IOException {
        return clientTradeDetailsService.getAllClientTradeDetails();
    }
}
