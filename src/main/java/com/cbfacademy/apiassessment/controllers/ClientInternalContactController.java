package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientInternalContact;
import com.cbfacademy.apiassessment.services.ClientInternalContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("client-internal-contact")
public class ClientInternalContactController {
    private final ClientInternalContactService clientInternalContactService;

    @Autowired
    public ClientInternalContactController(ClientInternalContactService clientInternalContactService) {
        this.clientInternalContactService = clientInternalContactService;
    }

    @GetMapping("/all-clients")
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
    public ResponseEntity<List<ClientInternalContact>> findEmployeeByRole(@PathVariable String role) {
        try {
            List<ClientInternalContact> clients = clientInternalContactService.findEmployeeByRole(role);
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
