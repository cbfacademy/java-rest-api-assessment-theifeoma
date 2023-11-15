package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.dto.ClientDto;
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
public class ClientDtoRepository {
    private File jsonFile;
    private ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(ClientDtoRepository.class);

    public ClientDtoRepository(@Value(JSON_REPOSITORY) String filePath) {
        this.jsonFile = new File(filePath);
        this.objectMapper = new ObjectMapper();

        // Call the initialization method to perform CSV to Dto to JSON conversion
        initialiseDtoRepository();
    }

    private void initialiseDtoRepository() {
        // Check if the JSON file is empty
        if (jsonFile.length() == 0) {
            // If the JSON file is empty, perform the CSV to Dto to JSON conversion
            CSVDataConverter csvDataConverter = new CSVDataConverter();
            csvDataConverter.convertCSVToDtoToClientDtoJson(CLIENT_DTO_CSV_DATA_FILES_LIST, JSON_REPOSITORY);
        } else {
            // If the JSON file is not empty, log a message and continue using the existing JSON data
            log.info("JSON file is not empty. Skipping CSV to JSON conversion.");
        }
    }

    //Repository option to always overwrite with CSV on startup if needed

//    private void initialiseDtoRepository() {
//        CSVDataConverter csvDataConverter = new CSVDataConverter();
//        csvDataConverter.convertCSVToDtoToJson(csvTestFiles, jsonDtoRepoTestFile);
//    }

    public List<ClientDto> getAllDto() throws IOException {
        List<ClientDto> clientDtoList = objectMapper.readValue(jsonFile, new TypeReference<List<ClientDto>>() {
        });
        return clientDtoList != null ? clientDtoList : new ArrayList<>();
    }

    public boolean existsByClientId(Long clientId) throws IOException {
        List<ClientDto> clients = getAllDto();

        // Sort the list by clientId
        clients.sort(Comparator.comparing(ClientDto::getClientId));

        // Perform binary search
        int index = binarySearchByClientId(clients, clientId);

        // Check if the clientId was found
        return index >= 0;
    }

    private int binarySearchByClientId(List<ClientDto> clients, Long clientId) {
        int start = 0;
        int end = clients.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            //gets the client id in the mid-index of list
            Long midVal = clients.get(mid).getClientId();
            //compares the mid-val with the clientId we are looking for
            int cmp = midVal.compareTo(clientId);

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

    public void saveClientDto(List<ClientDto> data) throws IOException {
        objectMapper.writeValue(jsonFile, data);
    }

//    public List<ClientDto> findAllByRole(String role) throws IOException {
//        List<ClientDto> clients = getAllDto();
//        return clients.stream()
//                .filter(client -> client != null && client.getRole().equalsIgnoreCase(role))
//                .collect(Collectors.toList());
//    }

    public boolean updateClientEmail(Long clientId, String newEmail) throws IOException {
        List<ClientDto> clients = getAllDto();

        // Find the client by ID
        Optional<ClientDto> clientOptional = clients.stream()
                .filter(client -> client != null && client.getClientId().equals(clientId))
                .findFirst();

        if (clientOptional.isPresent()) {
            ClientDto clientToUpdate = clientOptional.get();

            // Update the email address
            clientToUpdate.setEmail(newEmail);

            // Save the updated client list
            saveClientDto(clients);

            return true; // Update successful
        }
        return false; // Client not found
    }
}
