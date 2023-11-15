package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientInternalContact;
import com.cbfacademy.apiassessment.services.ClientInternalContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    public List<ClientInternalContact> getAllClients() throws IOException {
        return clientInternalContactService.getAllClientInternalContact();
    }

    @GetMapping("/all-role/{role}")
    public List<ClientInternalContact> findEmployeeByRole(@PathVariable String role) throws IOException{
        return clientInternalContactService.findEmployeeByRole(role);
    }
}
