package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.entities.ClientDetails;
import com.cbfacademy.apiassessment.helpers.CSVDataConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientDtoRepository {
    private File jsonFile;
    private ObjectMapper objectMapper;

    List<String> csvTestFiles = Arrays.asList("resources/csvFiles/clientAddress.csv", "resources/csvFiles/clientDetails.csv");
    List<String> csvFiles = Arrays.asList("resources/csvFiles/clientAddress.csv", "resources/csvFiles/clientDetails.csv", "resources/csvFiles/employeeDetails.csv", "resources/csvFiles/legalDetails.csv", "resources/csvFiles/tradeDetails.csv");
    String jsonRepoFile = "resources/jsonFiles/repo.json";
    String jsonRepoTestFile = "resources/jsonFiles/test.json";

    public ClientDtoRepository(@Value("${json.file.two.path}") String filePath) {
        this.jsonFile = new File(filePath);
        this.objectMapper = new ObjectMapper();

        // Call the initialization method to perform CSV to JSON conversion
        //initializeRepository();
        initialize2Repository();
    }

    private void initializeRepository() {
        CSVDataConverter csvDataConverter = new CSVDataConverter();
        csvDataConverter.convertCSVToJson(csvFiles, jsonRepoFile);
    }

    private void initialize2Repository(){
        CSVDataConverter csvDataConverter = new CSVDataConverter();
        csvDataConverter.convert2CSVToJson(csvTestFiles, jsonRepoTestFile);
    }

    public List<ClientDetails> getAll() throws IOException {
        List<ClientDetails> clientDetailsList = objectMapper.readValue(jsonRepoTestFile, new TypeReference<List<ClientDetails>>() {
        });
        return clientDetailsList != null ? clientDetailsList : new ArrayList<>();
    }

    public void save(List<ClientDetails> data) throws IOException {
        objectMapper.writeValue(jsonFile, data);
    }

    public boolean existsById(Long clientId) throws IOException {
        List<ClientDetails> clients = getAll();

        return clients.stream()
                .filter(client -> client instanceof ClientDetails && ((ClientDetails) client).getClientId().equals(clientId))
                .findFirst()
                .isPresent();
    }

    public List<Object> findAllByRole(String role) throws IOException {
        List<ClientDetails> clients = getAll();
        return clients.stream()
                .filter(client -> client instanceof ClientDetails && ((ClientDetails) client).getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }

    public Object findClientDetailsByEmail(String email) throws IOException {
        List<ClientDetails> clients = getAll();
        return clients.stream()
                .filter(client -> client instanceof ClientDetails && ((ClientDetails) client).getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}
