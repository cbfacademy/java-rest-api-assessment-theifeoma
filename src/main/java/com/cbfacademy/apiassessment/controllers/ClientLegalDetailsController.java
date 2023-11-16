package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientLegalDetails;
import com.cbfacademy.apiassessment.services.ClientLegalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<ClientLegalDetails> getAllClients(){
        return clientLegalDetailsService.getAllClientLegalDetails();
    }

    @GetMapping("/all-clients/status/{status}")
    public List<ClientLegalDetails> getAllClientsByStatus(@PathVariable String status) {
        return clientLegalDetailsService.getAllClientsByStatus(status);
    }

    @GetMapping("/sort/{classification}")
    public List<ClientLegalDetails> findClientsByClassification(@PathVariable String classification){
        return clientLegalDetailsService.findClientsByClassification(classification);
    }

    @GetMapping("/all-clients/risk-rating/{riskRating}")
    public List<ClientLegalDetails> getAllClientsByRiskRating(@PathVariable String riskRating) {
        return clientLegalDetailsService.getAllClientsByRiskRating(riskRating);
    }
}
