package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientLegalDetails;
import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.services.ClientLegalDetailsService;
import com.cbfacademy.apiassessment.services.ClientTradeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("client-legal-details")
public class ClientLegalDetailsController {
    private final ClientLegalDetailsService clientLegalDetailsService;

    @Autowired
    public ClientLegalDetailsController(ClientLegalDetailsService clientLegalDetailsService) {
        this.clientLegalDetailsService = clientLegalDetailsService;
    }

    @GetMapping("/all-clients")
    public List<ClientLegalDetails> getAllClients() throws IOException {
        return clientLegalDetailsService.getAllClientLegalDetails();
    }
}
