package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientInternalContact;
import com.cbfacademy.apiassessment.services.ClientInternalContactService;
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
@RequestMapping("client-internal-contact")
@Api(value = "Client Internal Contact", protocols = "http", tags = "Client Internal Contact API")
public class ClientInternalContactController {
    private final ClientInternalContactService clientInternalContactService;

    @Autowired
    public ClientInternalContactController(ClientInternalContactService clientInternalContactService) {
        this.clientInternalContactService = clientInternalContactService;
    }

    @GetMapping("/all-clients")
    @ApiOperation(value = "Get All Clients", notes = "Retrieve a list of all client internal contacts.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of clients."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<ClientInternalContact>> getAllClients() {
        try {
            List<ClientInternalContact> clients = clientInternalContactService.getAllClientInternalContact();
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all-role/{role}")
    @ApiOperation(value = "Find Clients by Role", notes = "Retrieve a list of client internal contacts by role.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of clients."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<ClientInternalContact>> findEmployeeByRole(@PathVariable String role) {
        try {
            List<ClientInternalContact> clients = clientInternalContactService.findEmployeeByRole(role);
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/replace-employee")
    @ApiOperation(value = "Replace Employee Information", notes = "Replace the information of an employee except for the clientId.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee replacement successful"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<String> replaceEmployee(@RequestParam Long oldEmployeeId, @RequestParam Long newEmployeeId) {
        try {
            String result = clientInternalContactService.replaceEmployee(oldEmployeeId, newEmployeeId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
