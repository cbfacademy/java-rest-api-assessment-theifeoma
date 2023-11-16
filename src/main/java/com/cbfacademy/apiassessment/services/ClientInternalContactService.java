package com.cbfacademy.apiassessment.services;

import com.cbfacademy.apiassessment.dto.ClientInternalContact;
import com.cbfacademy.apiassessment.repositories.ClientInternalContactRepository;
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
    public ClientInternalContactService(ClientInternalContactRepository clientInternalContactRepository) {
        this.clientInternalContactRepository = clientInternalContactRepository;
    }

    public List<ClientInternalContact> getAllClientInternalContact() {
        try {
            return clientInternalContactRepository.getAll();
        } catch (IOException e) {
            log.error("Error getting all client internal contacts: {}", e.getMessage());
            throw new RuntimeException("An error occurred while retrieving client internal contacts.");
        }
    }

    public List<ClientInternalContact> findEmployeeByRole(String role) {
        try {
            List<ClientInternalContact> employees = clientInternalContactRepository.findAllByRole(role);

            if (employees.isEmpty()) {
                throw new NoSuchElementException("No employee found with role: " + role);
            }

            return employees;
        } catch (IOException e) {
            log.error("Error finding employees by role: {}", e.getMessage());
            throw new RuntimeException("An error occurred while finding employees by role.");
        }
    }

    public ClientInternalContact getEmployeeById(Long employeeId){
        try {
            // Check if the employee exists
            if (!clientInternalContactRepository.existsByEmployeeId(employeeId)) {
                throw new IllegalStateException("Employee with Employee ID: " + employeeId + " does not exist");
            }

            // Retrieve the employee from the repository
            return clientInternalContactRepository.getByEmployeeId(employeeId);
        } catch (IOException e) {
            log.error("Error getting employee by ID: {}", e.getMessage());
            throw new RuntimeException("An error occurred while retrieving employee by ID.");
        }
    }
}
