package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.helpers.CSVDataConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.cbfacademy.apiassessment.constants.Const.*;
import static com.cbfacademy.apiassessment.constants.Const.TRADE_JSON_REPOSITORY;

@Repository
public class ClientTradeDetailsRepository implements RepositoryInterface{

    private final File jsonFile;
    private final ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(ClientTradeDetailsRepository.class);

    public ClientTradeDetailsRepository(@Value(TRADE_JSON_REPOSITORY) String filePath) {
        this.jsonFile = new File(filePath);
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Call the initialization method to perform CSV to Dto to JSON conversion
        initialiseRepository();
    }

    @Override
    public void initialiseRepository() {
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

    // get by timeOfExecution range and productGrouping
    public List<ClientTradeDetails> getByTimeAndProduct(LocalDate startDate, LocalDate endDate, String productGrouping) throws IOException {
        List<ClientTradeDetails> clientTradeDetailsList = getAll();

        // Filter by timeOfExecution range and productGrouping criteria
        return clientTradeDetailsList.stream()
                .peek(details -> details.setListOfClientTrades(
                        details.getListOfClientTrades().stream()
                                .filter(tradeDetailsDto -> tradeDetailsDto.getTimeOfExecution().isAfter(startDate)
                                        && tradeDetailsDto.getTimeOfExecution().isBefore(endDate)
                                        && tradeDetailsDto.getProductGrouping().equalsIgnoreCase(productGrouping))
                                .collect(Collectors.toList())))
                .filter(details -> !details.getListOfClientTrades().isEmpty())
                .collect(Collectors.toList());
    }

    // get by revenue range and productGrouping
    public List<ClientTradeDetails> getByRevenueAndProduct(Long minRevenue, Long maxRevenue, String productGrouping) throws IOException {
        List<ClientTradeDetails> clientTradeDetailsList = getAll();

        // Filter by revenue range and productGrouping criteria
        return clientTradeDetailsList.stream()
                .peek(details -> details.setListOfClientTrades(
                        details.getListOfClientTrades().stream()
                                .filter(tradeDetailsDto -> tradeDetailsDto.getProductGrouping().equalsIgnoreCase(productGrouping)
                                        && tradeDetailsDto.getRevenue() >= minRevenue && tradeDetailsDto.getRevenue() <= maxRevenue)
                                .collect(Collectors.toList())))
                .filter(details -> !details.getListOfClientTrades().isEmpty())
                .collect(Collectors.toList());
    }

    // get by client id and productGrouping
    public List<ClientTradeDetails> getByClientIdAndProduct(Long clientId, String productGrouping) throws IOException {
        List<ClientTradeDetails> clientTradeDetailsList = getAll();

        // Filter by client id and productGrouping criteria
        return clientTradeDetailsList.stream()
                .filter(details -> details.getClientId().equals(clientId))
                .peek(details -> details.setListOfClientTrades(
                        details.getListOfClientTrades().stream()
                                .filter(tradeDetailsDto -> tradeDetailsDto.getProductGrouping().equalsIgnoreCase(productGrouping))
                                .collect(Collectors.toList())))
                .filter(details -> !details.getListOfClientTrades().isEmpty())
                .collect(Collectors.toList());
    }

    // get by client id and timeOfExecution range
    public List<ClientTradeDetails> getByClientIdAndTimeRange(Long clientId, LocalDate startDate, LocalDate endDate) throws IOException {
        List<ClientTradeDetails> clientTradeDetailsList = getAll();

        // Filter by client id and timeOfExecution range criteria
        return clientTradeDetailsList.stream()
                .filter(details -> details.getClientId().equals(clientId))
                .peek(details -> details.setListOfClientTrades(
                        details.getListOfClientTrades().stream()
                                .filter(tradeDetailsDto -> tradeDetailsDto.getTimeOfExecution().isAfter(startDate)
                                        && tradeDetailsDto.getTimeOfExecution().isBefore(endDate))
                                .collect(Collectors.toList())))
                .filter(details -> !details.getListOfClientTrades().isEmpty())
                .collect(Collectors.toList());
    }
}
