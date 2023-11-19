package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientLegalDetails;
import com.cbfacademy.apiassessment.services.ClientLegalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ClientLegalDetails>> getAllClients(){
        try {
            List<ClientLegalDetails> clients = clientLegalDetailsService.getAllClientLegalDetails();
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all-clients/status/{status}")
    public ResponseEntity<List<ClientLegalDetails>> getAllClientsByStatus(@PathVariable String status) {
        try {
            List<ClientLegalDetails> clients = clientLegalDetailsService.getAllClientsByStatus(status);
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/sort/{classification}")
    public ResponseEntity<List<ClientLegalDetails>> findClientsByClassification(@PathVariable String classification) {
        try {
            List<ClientLegalDetails> clients = clientLegalDetailsService.findClientsByClassification(classification);
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all-clients/risk-rating/{riskRating}")
    public ResponseEntity<List<ClientLegalDetails>> getAllClientsByRiskRating(@PathVariable String riskRating) {
        try {
            List<ClientLegalDetails> clients = clientLegalDetailsService.getAllClientsByRiskRating(riskRating);
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
