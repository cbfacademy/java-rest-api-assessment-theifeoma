package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.services.ClientDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client-details")
@Api(value = "Client Details", protocols = "http", tags = "Client Details API")
public class ClientDtoController {

    private final ClientDetailsService clientDetailsService;

    @Autowired
    public ClientDtoController(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @GetMapping("/all-clients")
    @ApiOperation(value = "Get All Clients", notes = "Retrieve a list of all clients.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of clients."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
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
    @ApiOperation(value = "Add New Client", notes = "Add a new client.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Client added successfully."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
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
    @ApiOperation(value = "Delete Client", notes = "Delete a client by ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Client deleted successfully."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
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
    @ApiOperation(value = "Update Client Email", notes = "Update the email of a client.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client email updated successfully."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
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
    @ApiOperation(value = "Get Client by ID", notes = "Retrieve a client by ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the client."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
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
