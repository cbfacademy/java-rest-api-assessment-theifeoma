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
import java.util.stream.Collectors;

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

    //get by product
    public List<ClientTradeDetails> getByProduct(String product) throws IOException {
        List<ClientTradeDetails> clientTradeDetailsList = getAll();

        // Filter by product criteria
        return clientTradeDetailsList.stream()
                .filter(details -> details.getProduct().equalsIgnoreCase(product))
                .collect(Collectors.toList());
    }

    //get by revenue min and max
    public List<ClientTradeDetails> getByRevenueRange(Long minRevenue, Long maxRevenue) throws IOException {
        List<ClientTradeDetails> clientTradeDetailsList = getAll();

        // Filter by revenue range criteria
        return clientTradeDetailsList.stream()
                .filter(details -> details.getRevenue() >= minRevenue && details.getRevenue() <= maxRevenue)
                .collect(Collectors.toList());
    }
}
