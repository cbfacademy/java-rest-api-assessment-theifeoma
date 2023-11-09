package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.entities.ClientDetails;
import com.cbfacademy.apiassessment.helpers.CSVDataConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientDtoRepository {
    private File jsonFile;
    private ObjectMapper objectMapper;

    List<String> csvFiles = Arrays.asList("src/main/resources/csvFiles/clientAddress.csv", "src/main/resources/csvFiles/clientDetails.csv", "src/main/resources/csvFiles/employeeDetails.csv", "src/main/resources/csvFiles/legalDetails.csv", "src/main/resources/csvFiles/tradeDetails.csv");
    String jsonRepoFile = "src/main/resources/jsonFiles/repo.json";

    public ClientDtoRepository(@Value("${json.file.path}") String filePath) {
        this.jsonFile = new File(filePath);
        this.objectMapper = new ObjectMapper();

        // Call the initialization method to perform CSV to JSON conversion
        initializeRepository();
    }

    private void initializeRepository() {
        CSVDataConverter csvDataConverter = new CSVDataConverter();
        csvDataConverter.convertCSVToJson(csvFiles, jsonRepoFile);
    }

    public List<Object> getAll() throws IOException {
        return objectMapper.readValue(jsonFile, List.class);
    }

    public void save(List<Object> data) throws IOException {
        objectMapper.writeValue(jsonFile, data);
    }

    public boolean existsById(Long clientId) throws IOException {
        List<Object> clients = getAll();

        return clients.stream()
                .filter(client -> client instanceof ClientDetails && ((ClientDetails) client).getClientId().equals(clientId))
                .findFirst()
                .isPresent();
    }

    public List<Object> findAllByRole(String role) throws IOException {
        List<Object> clients = getAll();
        return clients.stream()
                .filter(client -> client instanceof ClientDetails && ((ClientDetails) client).getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }

    public Object findClientDetailsByEmail(String email) throws IOException {
        List<Object> clients = getAll();
        return clients.stream()
                .filter(client -> client instanceof ClientDetails && ((ClientDetails) client).getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}
