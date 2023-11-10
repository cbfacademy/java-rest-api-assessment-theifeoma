package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.entities.ClientDetails;
import com.cbfacademy.apiassessment.helpers.CSVDataConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientDtoRepository {
    private File jsonFile;
    private ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(ClientDtoRepository.class);

    List<String> csvTestFiles = Arrays.asList("src/main/resources/csvFiles/clientAddress.csv", "src/main/resources/csvFiles/clientDetails.csv");
    List<String> csvFiles = Arrays.asList("resources/csvFiles/clientAddress.csv", "resources/csvFiles/clientDetails.csv", "resources/csvFiles/employeeDetails.csv", "resources/csvFiles/legalDetails.csv", "resources/csvFiles/tradeDetails.csv");
    String jsonRepoFile = "resources/jsonFiles/repo.json";
    String jsonRepoTestFile = "resources/jsonFiles/test.json";
    String jsonDtoRepoTestFile = "src/main/resources/jsonFiles/clientDtoRepo.json";

    public ClientDtoRepository(@Value("${json.file.clientDtoRepo.path}") String filePath) {
        this.jsonFile = new File(filePath);
        this.objectMapper = new ObjectMapper();

        // Call the initialization method to perform CSV to JSON conversion
        //initializeRepository();
        //initialize2Repository();
        initialiseDtoRepository();
    }

    private void initializeRepository() {
        CSVDataConverter csvDataConverter = new CSVDataConverter();
        csvDataConverter.convertCSVToJson(csvFiles, jsonRepoFile);
    }

    private void initialize2Repository() {
        CSVDataConverter csvDataConverter = new CSVDataConverter();
        csvDataConverter.convert2CSVToJson(csvTestFiles, jsonRepoTestFile);
    }

    private void initialiseDtoRepository() {
        CSVDataConverter csvDataConverter = new CSVDataConverter();
        log.info("Inside initialiseDtoRepository");
        csvDataConverter.convertCSVToDtoToJson(csvTestFiles, jsonDtoRepoTestFile);
    }

    public List<ClientDetails> getAll() throws IOException {
        List<ClientDetails> clientDetailsList = objectMapper.readValue(jsonRepoTestFile, new TypeReference<List<ClientDetails>>() {
        });
        return clientDetailsList != null ? clientDetailsList : new ArrayList<>();
    }

    public List<ClientDto> getAllDto() throws IOException {
        log.info("Inside Repository");

        // Read the content of the file into a String
        String json = Files.readString(Paths.get(jsonDtoRepoTestFile));

        List<ClientDto> clientDtoList = objectMapper.readValue(json, new TypeReference<List<ClientDto>>() {
        });

        //Debugging
        log.info("Size of clientDtos list: {}", clientDtoList.size());
        log.info("Contents of clientDtos list: {}", clientDtoList);
        return clientDtoList != null ? clientDtoList : new ArrayList<>();
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
