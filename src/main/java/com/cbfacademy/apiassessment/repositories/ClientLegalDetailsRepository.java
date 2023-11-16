package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.dto.ClientLegalDetails;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.cbfacademy.apiassessment.constants.Const.*;

@Repository
public class ClientLegalDetailsRepository implements RepositoryInterface{

    private final File jsonFile;
    private final ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(ClientLegalDetailsRepository.class);

    public ClientLegalDetailsRepository(@Value(LEGAL_JSON_REPOSITORY) String filePath) {
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
            csvDataConverter.convertCSVToDtoToClientLegalJson(CLIENT_LEGAL_DETAILS_CSV_DATA_FILES_LIST, LEGAL_JSON_REPOSITORY);
        } else {
            // If the JSON file is not empty, log a message and continue using the existing JSON data
            log.info("Legal Details JSON file is not empty. Skipping CSV to JSON conversion.");
        }
    }

    public List<ClientLegalDetails> getAll() throws IOException {
        try {
            List<ClientLegalDetails> clientLegalDetailsList = objectMapper.readValue(jsonFile, new TypeReference<List<ClientLegalDetails>>() {
            });
            return clientLegalDetailsList != null ? clientLegalDetailsList : new ArrayList<>();
        } catch (IOException e) {
            log.error("Error reading clientLegalDetails data from JSON file: {}", e.getMessage());
            throw new RuntimeException("An error occurred while reading clientLegalDetails data.", e);
        }
    }

    //get by risk rating
    public List<ClientLegalDetails> getByRiskRating(String riskRating) throws IOException{
        List<ClientLegalDetails> clientLegalDetailsList = getAll();

        // Filter by risk rating criteria
        List<ClientLegalDetails> filteredList = clientLegalDetailsList.stream()
                .filter(details -> riskRating.equalsIgnoreCase(details.getRiskRating()))
                .collect(Collectors.toList());

        // Sort the filtered clients by classification
        filteredList.sort(Comparator.comparing(ClientLegalDetails::getRiskRating));

        return filteredList;
    }

    //get by status
    public List<ClientLegalDetails> getByStatus(String status) throws IOException{
        List<ClientLegalDetails> clientLegalDetailsList = getAll();

        // Filter by status criteria
        List<ClientLegalDetails> filteredList = clientLegalDetailsList.stream()
                .filter(details -> status.equalsIgnoreCase(details.getClientStatus()))
                .collect(Collectors.toList());

        // Sort the filtered clients by classification
        filteredList.sort(Comparator.comparing(ClientLegalDetails::getClientStatus));

        return filteredList;
    }

    public List<ClientLegalDetails> findClientsByClassification(String classification) throws IOException{
        List<ClientLegalDetails> clients = getAll();

        // Filter clients by the given classification
        List<ClientLegalDetails> filteredClients = clients.stream()
                .filter(client -> classification.equalsIgnoreCase(client.getClientLegalClassification()))
                .collect(Collectors.toList());

        // Sort the filtered clients by classification
        filteredClients.sort(Comparator.comparing(ClientLegalDetails::getClientLegalClassification));

        return filteredClients;
    }
}
