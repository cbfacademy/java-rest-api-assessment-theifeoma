package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.helpers.CSVDataConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.cbfacademy.apiassessment.constants.Const.*;
import static com.cbfacademy.apiassessment.constants.Const.TRADE_JSON_REPOSITORY;

@Repository
public class ClientTradeDetailsRepository {

    private File jsonFile;
    private ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(ClientTradeDetailsRepository.class);

    public ClientTradeDetailsRepository(@Value(TRADE_JSON_REPOSITORY) String filePath) {
        this.jsonFile = new File(filePath);
        this.objectMapper = new ObjectMapper();

        // Call the initialization method to perform CSV to Dto to JSON conversion
        initialiseTradeDetailsRepository();
    }

    private void initialiseTradeDetailsRepository() {
        // Check if the JSON file is empty
        if (jsonFile.length() == 0) {
            // If the JSON file is empty, perform the CSV to Dto to JSON conversion
            CSVDataConverter csvDataConverter = new CSVDataConverter();
            csvDataConverter.convertCSVToDtoToClientTradeJson(CLIENT_TRADE_DETAILS_CSV_DATA_FILES_LIST, TRADE_JSON_REPOSITORY);
        } else {
            // If the JSON file is not empty, log a message and continue using the existing JSON data
            log.info("Trade Details JSON file is not empty. Skipping CSV to JSON conversion.");
        }
    }

    public List<ClientTradeDetails> getAll() throws IOException {
        List<ClientTradeDetails> clientTradeDetailsList = objectMapper.readValue(jsonFile, new TypeReference<List<ClientTradeDetails>>() {
        });
        return clientTradeDetailsList != null ? clientTradeDetailsList : new ArrayList<>();
    }

//    public boolean existsByClientId(Long clientId) throws IOException {
//        List<ClientTradeDetails> clients = getAllDto();
//
//        // Sort the list by clientId
//        clients.sort(Comparator.comparing(ClientTradeDetails::getClientId));
//
//        // Perform binary search
//        int index = binarySearchByClientId(clients, clientId);
//
//        // Check if the clientId was found
//        return index >= 0;
//    }
//
//    private int binarySearchByClientId(List<ClientTradeDetails> clients, Long clientId) {
//        int start = 0;
//        int end = clients.size() - 1;
//
//        while (start <= end) {
//            int mid = (start + end) / 2;
//            //gets the client id in the mid-index of list
//            Long midVal = clients.get(mid).getClientId();
//            //compares the mid-val with the clientId we are looking for
//            int cmp = midVal.compareTo(clientId);
//
//            if (cmp < 0) {
//                start = mid + 1;
//            } else if (cmp > 0) {
//                end = mid - 1;
//            } else {
//                return mid; // Found
//            }
//        }
//        return -1; // Not found
//    }
//
//    public void saveClientDto(List<ClientTradeDetails> data) throws IOException {
//        objectMapper.writeValue(jsonFile, data);
//    }
//
//    public List<Object> findAllByRole(String role) throws IOException {
//        List<ClientTradeDetails> clients = getAllDto();
//        return clients.stream()
//                .filter(client -> client != null && client.getRole().equalsIgnoreCase(role))
//                .collect(Collectors.toList());
//    }
//
//    public List<ClientTradeDetails> findClientsByClassification(String classification) throws IOException{
//        List<ClientTradeDetails> clients = getAllDto();
//
//        // Filter clients by the given classification
//        List<ClientDto> filteredClients = clients.stream()
//                .filter(client -> classification.equalsIgnoreCase(client.getClientClassification()))
//                .collect(Collectors.toList());
//
//        // Sort the filtered clients by classification
//        filteredClients.sort(Comparator.comparing(ClientDto::getClientClassification));
//
//        return filteredClients;
//    }
//
//    public boolean updateClientEmail(Long clientId, String newEmail) throws IOException {
//        List<ClientTradeDetails> clients = getAllDto();
//
//        // Find the client by ID
//        Optional<ClientTradeDetails> clientOptional = clients.stream()
//                .filter(client -> client != null && client.getClientId().equals(clientId))
//                .findFirst();
//
//        if (clientOptional.isPresent()) {
//            ClientTradeDetails clientToUpdate = clientOptional.get();
//
//            log.info(clientToUpdate.toString());
//
//            // Update the email address
//            clientToUpdate.setEmail(newEmail);
//
//            log.info(clientToUpdate.toString());
//
//            // Save the updated client list
//            saveClientDto(clients);
//
//            log.info(clientToUpdate.toString());
//
//            return true; // Update successful
//        }
//
//        return false; // Client not found
//    }
}
