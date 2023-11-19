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
import java.util.Optional;

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
    public String replaceEmployee(Long oldEmployeeId, Long newEmployeeId) {
        try {
            // Check if the old employee exists
            Optional<ClientInternalContact> oldEmployeeOptional = clientInternalContactRepository.getByEmployeeIdOptional(oldEmployeeId);

            if (oldEmployeeOptional.isPresent()) {
                // Old employee exists
                ClientInternalContact oldEmployee = oldEmployeeOptional.get();

                // Check if the new employee exists
                Optional<ClientInternalContact> newEmployeeOptional = clientInternalContactRepository.getByEmployeeIdOptional(newEmployeeId);

                if (newEmployeeOptional.isPresent()) {
                    // New employee exists
                    ClientInternalContact newEmployee = newEmployeeOptional.get();

                    // Replace the information in the old employee with the information from the new employee
                    oldEmployee.setEmployeeId(newEmployee.getEmployeeId());
                    oldEmployee.setFirstName(newEmployee.getFirstName());
                    oldEmployee.setMiddleName(newEmployee.getMiddleName());
                    oldEmployee.setFamilyName(newEmployee.getFamilyName());
                    oldEmployee.setEmail(newEmployee.getEmail());
                    oldEmployee.setRole(newEmployee.getRole());
                    oldEmployee.setRoleId(newEmployee.getRoleId());
                    oldEmployee.setTelephoneNumber(newEmployee.getTelephoneNumber());

                    // Get the list of all internal contacts
                    List<ClientInternalContact> listOfInternalContacts = clientInternalContactRepository.getAll();

                    // Remove the old employee from the list
                    listOfInternalContacts.remove(oldEmployee);

                    // Add the modified old employee back to the list
                    listOfInternalContacts.add(oldEmployee);

                    // Save the updated list back to the repository
                    clientInternalContactRepository.saveClientInternalContact(listOfInternalContacts);

                    return "Employee replacement successful";
                } else {
                    // New employee does not exist
                    throw new IllegalStateException("New Employee with ID: " + newEmployeeId + " does not exist");
                }
            } else {
                // Old employee does not exist
                throw new IllegalStateException("Old Employee with ID: " + oldEmployeeId + " does not exist");
            }

        } catch (IOException e) {
            log.error("Error replacing employee: {}", e.getMessage());
            throw new RuntimeException("An error occurred while replacing employee.");
        }
    }
}
