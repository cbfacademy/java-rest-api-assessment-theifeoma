package com.cbfacademy.apiassessment.services;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.dto.ClientInternalContact;
import com.cbfacademy.apiassessment.dto.ClientLegalDetails;
import com.cbfacademy.apiassessment.repositories.ClientInternalContactRepository;
import com.cbfacademy.apiassessment.repositories.ClientLegalDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientInternalContactService {
    private final ClientInternalContactRepository clientInternalContactRepository;
    private static final Logger log = LoggerFactory.getLogger(ClientInternalContactService.class);

    @Autowired
    public ClientInternalContactService(ClientInternalContactRepository clientInternalContactRepository){
        this.clientInternalContactRepository = clientInternalContactRepository;
    }

    public List<ClientInternalContact> getAllClientInternalContact() throws IOException {
        return clientInternalContactRepository.getAll();
    }

    public List<ClientInternalContact> findEmployeeByRole(String role) throws IOException {
        List<ClientInternalContact> employees = clientInternalContactRepository.findAllByRole(role);

        if (employees.isEmpty()) {
            throw new NoSuchElementException("No employee found with role: " + role);
        }

        return employees;
    }

    //get by employeeId
//    public ClientInternalContact findEmployeeById(Long employeeId) throws IOException{
//        boolean employeeExists = clientInternalContactRepository.existsByEmployeeId(employeeId);
//
//        if(!employeeExists){
//            throw new IllegalStateException("Employee with Employee ID: " + employeeId + " exists");
//        }
//
//        return null;
//    }
}
