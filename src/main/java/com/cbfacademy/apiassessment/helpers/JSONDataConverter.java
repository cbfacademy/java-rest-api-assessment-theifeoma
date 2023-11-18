package com.cbfacademy.apiassessment.helpers;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.entities.ClientAddress;
import com.cbfacademy.apiassessment.entities.ClientDetails;
import com.cbfacademy.apiassessment.mappers.ClientMapper;
import com.cbfacademy.apiassessment.mappers.ClientMapperImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONDataConverter {

    private static final Logger log = LoggerFactory.getLogger(JSONDataConverter.class);

//    public <T> List<T> convertJSONToDto(String jsonFile, Class<T> dtoClass) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<T> dtoList = new ArrayList<>();
//
//        try {
//            List<Map<String, Object>> jsonData = objectMapper.readValue(new File(jsonFile), new TypeReference<List<Map<String, Object>>>() {});
//
//            for (Map<String, Object> data : jsonData) {
//                T dto = objectMapper.convertValue(data, dtoClass);
//                dtoList.add(dto);
//            }
//
//            // Debugging
//            log.info("Size of {} list: {}", dtoClass.getSimpleName(), dtoList.size());
//            log.info("Contents of {} list: {}", dtoClass.getSimpleName(), dtoList);
//        } catch (IOException e) {
//            log.error("Error reading data from JSON file {}: {}", jsonFile, e.getMessage());
//            throw new RuntimeException("An error occurred while reading data from JSON file.", e);
//        }
//
//        return dtoList;
//    }
//
//    public <T> void convertDtoToJSON(List<T> dtoList, String jsonFile) {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
//            String jsonData = objectMapper.writeValueAsString(dtoList);
//            fileWriter.write(jsonData);
//        } catch (IOException e) {
//            log.error("Error saving data to JSON file {}: {}", jsonFile, e.getMessage());
//            throw new RuntimeException("An error occurred while saving data to JSON file.", e);
//        }
//    }

    public void convertJSONToDtoToClientDtoJson(List<String> jsonFiles, String jsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ClientDto> clientDtos = new ArrayList<>();

        ClientMapper clientMapper = new ClientMapperImpl();

        List<ClientDetails> clientDetailsList = new ArrayList<>();
        List<ClientAddress> clientAddressList = new ArrayList<>();

        // Loop through each JSON file and parse its data into the respective class lists
        for (String jsonfile : jsonFiles) {
            try (Reader reader = new FileReader(jsonfile)) {
                // Parse JSON data into the respective lists based on the file type
                if (jsonfile.endsWith("clientDetails.json")) {
                    clientDetailsList.addAll(parseJsonToClientDetails(reader));
                } else if (jsonfile.endsWith("clientAddress.json")) {
                    clientAddressList.addAll(parseJsonToClientAddresses(reader));
                } else {
                    System.out.println("Unrecognized JSON file: " + jsonfile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Map ClientDetails and ClientAddress to ClientDto and add to the final list
        for (int i = 0; i < clientDetailsList.size() && i < clientAddressList.size(); i++) {
            ClientDetails clientDetails = clientDetailsList.get(i);
            ClientAddress clientAddress = clientAddressList.get(i);

            clientDtos.add(clientMapper.mapToClientDto(clientDetails, clientAddress));
        }

        // Debugging
        log.info("Size of clientDtos list: {}", clientDtos.size());
        log.info("Contents of clientDtos list: {}", clientDtos);

        // Convert List of ClientDto objects to JSON
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            String jsonData = objectMapper.writeValueAsString(clientDtos);
            fileWriter.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<ClientDetails> parseJsonToClientDetails(Reader reader) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ClientDetails> clientDetailsList = new ArrayList<>();

        clientDetailsList = objectMapper.readValue(reader, new TypeReference<List<ClientDetails>>() {});

        return clientDetailsList;
    }

    private List<ClientAddress> parseJsonToClientAddresses(Reader reader) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ClientAddress> clientAddressList = new ArrayList<>();

        clientAddressList = objectMapper.readValue(reader, new TypeReference<List<ClientAddress>>() {});

        return clientAddressList;
    }

}

