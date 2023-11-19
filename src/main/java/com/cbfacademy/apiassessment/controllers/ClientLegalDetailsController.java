package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientLegalDetails;
import com.cbfacademy.apiassessment.services.ClientLegalDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Client Legal Details", protocols = "http", tags = "Client Legal Details API")
public class ClientLegalDetailsController {
    private final ClientLegalDetailsService clientLegalDetailsService;

    @Autowired
    public ClientLegalDetailsController(ClientLegalDetailsService clientLegalDetailsService) {
        this.clientLegalDetailsService = clientLegalDetailsService;
    }

    @GetMapping("/all-clients")
    @ApiOperation(value = "Get All Clients", notes = "Retrieve a list of all client legal details.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of clients."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
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
    @ApiOperation(value = "Get Clients by Status", notes = "Retrieve a list of client legal details by status.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of clients."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
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
    @ApiOperation(value = "Find Clients by Classification", notes = "Retrieve a list of client legal details by classification.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of clients."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
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
    @ApiOperation(value = "Get Clients by Risk Rating", notes = "Retrieve a list of client legal details by risk rating.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of clients."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
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
