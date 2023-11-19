package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.dto.ClientInternalContact;
import com.cbfacademy.apiassessment.helpers.CSVDataConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.cbfacademy.apiassessment.constants.Const.*;

@Repository
public class ClientInternalContactRepository implements RepositoryInterface{

    private final File jsonFile;
    private final ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(ClientInternalContactRepository.class);

    public ClientInternalContactRepository(@Value(INTERNAL_CONTACT_JSON_REPOSITORY) String filePath) {
        this.jsonFile = new File(filePath);
        this.objectMapper = new ObjectMapper();

        // Call the initialization method to perform CSV to Dto to JSON conversion
        initialiseRepository();
    }

    @Override
    public void initialiseRepository() {
        // Check if the JSON file is empty
        if (jsonFile.length() == 0) {
            // If the JSON file is empty, perform the CSV to Dto to JSON conversion
            CSVDataConverter csvDataConverter = new CSVDataConverter();
            csvDataConverter.convertCSVToDtoToClientInternalJson(CLIENT_INTERNAL_CONTACT_CSV_DATA_FILES_LIST, INTERNAL_CONTACT_JSON_REPOSITORY);
        } else {
            // If the JSON file is not empty, log a message and continue using the existing JSON data
            log.info("Internal Contact JSON file is not empty. Skipping CSV to JSON conversion.");
        }
    }

    public List<ClientInternalContact> getAll() throws IOException {
        try {
            List<ClientInternalContact> clientInternalContactList = objectMapper.readValue(jsonFile, new TypeReference<List<ClientInternalContact>>() {
            });
            return clientInternalContactList != null ? clientInternalContactList : new ArrayList<>();
        } catch (IOException e) {
            log.error("Error reading internal contact data from JSON file: {}", e.getMessage());
            throw new RuntimeException("An error occurred while reading internal contact data.", e);
        }
    }

    //get by role
    public List<ClientInternalContact> findAllByRole(String role) throws IOException {
        List<ClientInternalContact> employees = getAll();
        return employees.stream()
                .filter(client -> client != null && client.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }

    public boolean existsByEmployeeId(Long employeeId) throws IOException {
        List<ClientInternalContact> employees = getAll();

        // Sort the list by clientId
        employees.sort(Comparator.comparing(ClientInternalContact::getEmployeeId));

        // Perform binary search
        int index = binarySearchByEmployeeId(employees, employeeId);

        // Check if the employee was found
        return index >= 0;
    }

    private int binarySearchByEmployeeId(List<ClientInternalContact> employees, Long employeeId) {
        int start = 0;
        int end = employees.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            //gets the employee id in the mid-index of list
            Long midVal = employees.get(mid).getEmployeeId();
            //compares the mid-val with the employeeId we are looking for
            int cmp = midVal.compareTo(employeeId);

            if (cmp < 0) {
                start = mid + 1;
            } else if (cmp > 0) {
                end = mid - 1;
            } else {
                return mid; // Found
            }
        }
        return -1; // Not found
    }

    public ClientInternalContact getByEmployeeId(Long employeeId) throws IOException {
        List<ClientInternalContact> employees = getAll();

        // Filter the list based on the employeeId
        return employees.stream()
                .filter(client -> client != null && client.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No employee found with Employee ID: " + employeeId));
    }

    public Optional<ClientInternalContact> getByEmployeeIdOptional(Long employeeId) throws IOException {
        List<ClientInternalContact> employees = getAll();

        // Filter the list based on the employeeId
        return employees.stream()
                .filter(client -> client != null && client.getEmployeeId().equals(employeeId))
                .findFirst();
    }

    public void saveClientInternalContact(List<ClientInternalContact> data) throws IOException{
        try {
            objectMapper.writeValue(jsonFile, data);
        } catch (IOException e) {
            log.error("Error saving clientDto data to JSON file: {}", e.getMessage());
            throw new RuntimeException("An error occurred while saving clientDto data.", e);
        }
    }

}
