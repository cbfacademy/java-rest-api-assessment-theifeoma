package com.cbfacademy.apiassessment.helpers;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.dto.ClientInternalContact;
import com.cbfacademy.apiassessment.dto.ClientLegalDetails;
import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.entities.*;
import com.cbfacademy.apiassessment.mappers.ClientMapper;
import com.cbfacademy.apiassessment.mappers.ClientMapperImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVDataConverter {

    private static final Logger log = LoggerFactory.getLogger(CSVDataConverter.class);

    public void convertCSVToDtoToClientDtoJson(List<String> csvFiles, String jsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ClientDto> clientDtos = new ArrayList<>();

        ClientMapper clientMapper = new ClientMapperImpl();

        List<ClientDetails> clientDetailsList = new ArrayList<>();
        List<ClientAddress> clientAddressList = new ArrayList<>();

        // Loop through each CSV file and parse its data into the respective class lists
        for (String csvFile : csvFiles) {
            try (Reader reader = new FileReader(csvFile)) {
                // Parse CSV data into the respective lists based on the file type
                if (csvFile.endsWith("clientDetails.csv")) {
                    clientDetailsList.addAll(parseCsvToClientDetails(reader));
                } else if (csvFile.endsWith("clientAddress.csv")) {
                    clientAddressList.addAll(parseCsvToClientAddresses(reader));
                } else {
                    System.out.println("Unrecognized CSV file: " + csvFile);
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

    public void convertCSVToDtoToClientTradeJson(List<String> csvFiles, String jsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<ClientTradeDetails> clientTradeDetailsList = new ArrayList<>();

        ClientMapper clientMapper = new ClientMapperImpl();

        List<ClientDetails> clientDetailsList = new ArrayList<>();
        List<TradeDetails> tradeDetailsList = new ArrayList<>();

        // Loop through each CSV file and parse its data into the respective class lists
        for (String csvFile : csvFiles) {
            try (Reader reader = new FileReader(csvFile)) {
                // Parse CSV data into the respective lists based on the file type
                if (csvFile.endsWith("clientDetails.csv")) {
                    clientDetailsList.addAll(parseCsvToClientDetails(reader));
                } else if (csvFile.endsWith("tradeDetails.csv")) {
                    tradeDetailsList.addAll(parseCsvToTradeDetails(reader));
                } else {
                    System.out.println("Unrecognized CSV file: " + csvFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Map ClientDetails and TradeDetails to ClientTradeDetails and add to the final list
//        for (int i = 0; i < clientDetailsList.size() && i < tradeDetailsList.size(); i++) {
//            ClientDetails clientDetails = clientDetailsList.get(i);
//            TradeDetails tradeDetails = tradeDetailsList.get(i);
//
//            clientTradeDetailsList.add(clientMapper.mapToClientTradeDetails(clientDetails, tradeDetails));
//        }

        // Log account numbers for debugging
        log.info("Client Account Numbers: {}", clientDetailsList.stream().map(ClientDetails::getAccountNumber).collect(Collectors.toList()));
        log.info("Trade Account Numbers: {}", tradeDetailsList.stream().map(TradeDetails::getAccountNumber).collect(Collectors.toList()));

        for (ClientDetails clientDetails : clientDetailsList) {
            // Filter the tradeDetailsList to get trades for the current client
            List<TradeDetails> clientTradeDetails = tradeDetailsList.stream()
                    .filter(trade -> trade.getAccountNumber().equals(clientDetails.getAccountNumber()))
                    .collect(Collectors.toList());

            // Log for debugging
            log.info("Client Account Number: {}", clientDetails.getAccountNumber());
            log.info("Size of Filtered TradeDetails: {}", clientTradeDetails.size());

            // Map ClientDetails and filtered TradeDetails to ClientTradeDetails and add to the final list
            clientTradeDetailsList.add(clientMapper.mapToClientTradeDetails(clientDetails, clientTradeDetails));
        }

        // Debugging
        log.info("Size of ClientTradeDetails list: {}", clientTradeDetailsList.size());
        log.info("Contents of ClientTradeDetails list: {}", clientTradeDetailsList);

        // Convert List of ClientTradeDetails objects to JSON
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            String jsonData = objectMapper.writeValueAsString(clientTradeDetailsList);
            fileWriter.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertCSVToDtoToClientLegalJson(List<String> csvFiles, String jsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ClientLegalDetails> clientLegalDetailsList = new ArrayList<>();

        ClientMapper clientMapper = new ClientMapperImpl();

        List<ClientDetails> clientDetailsList = new ArrayList<>();
        List<LegalDetails> legalDetailsList = new ArrayList<>();

        // Loop through each CSV file and parse its data into the respective class lists
        for (String csvFile : csvFiles) {
            try (Reader reader = new FileReader(csvFile)) {
                // Parse CSV data into the respective lists based on the file type
                if (csvFile.endsWith("clientDetails.csv")) {
                    clientDetailsList.addAll(parseCsvToClientDetails(reader));
                } else if (csvFile.endsWith("legalDetails.csv")) {
                    legalDetailsList.addAll(parseCsvToLegalDetails(reader));
                } else {
                    System.out.println("Unrecognized CSV file: " + csvFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Map ClientDetails and TradeDetails to ClientTradeDetails and add to the final list
        for (int i = 0; i < clientDetailsList.size() && i < legalDetailsList.size(); i++) {
            ClientDetails clientDetails = clientDetailsList.get(i);
            LegalDetails legalDetails = legalDetailsList.get(i);

            clientLegalDetailsList.add(clientMapper.mapToClientLegalDetails(clientDetails, legalDetails));
        }

        // Debugging
        log.info("Size of clientLegalDetailsList list: {}", clientLegalDetailsList.size());
        log.info("Contents of clientLegalDetailsList list: {}", clientLegalDetailsList);

        // Convert List of ClientTradeDetails objects to JSON
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            String jsonData = objectMapper.writeValueAsString(clientLegalDetailsList);
            fileWriter.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertCSVToDtoToClientInternalJson(List<String> csvFiles, String jsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ClientInternalContact> clientInternalContactList = new ArrayList<>();

        ClientMapper clientMapper = new ClientMapperImpl();

        List<ClientDetails> clientDetailsList = new ArrayList<>();
        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();

        // Loop through each CSV file and parse its data into the respective class lists
        for (String csvFile : csvFiles) {
            try (Reader reader = new FileReader(csvFile)) {
                // Parse CSV data into the respective lists based on the file type
                if (csvFile.endsWith("clientDetails.csv")) {
                    clientDetailsList.addAll(parseCsvToClientDetails(reader));
                } else if (csvFile.endsWith("employeeDetails.csv")) {
                    employeeDetailsList.addAll(parseCsvToEmployeeDetails(reader));
                } else {
                    System.out.println("Unrecognized CSV file: " + csvFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Map ClientDetails and EmployeeDetails to ClientInternalContact and add to the final list
        for (int i = 0; i < clientDetailsList.size() && i < employeeDetailsList.size(); i++) {
            ClientDetails clientDetails = clientDetailsList.get(i);
            EmployeeDetails employeeDetails = employeeDetailsList.get(i);

            clientInternalContactList.add(clientMapper.mapToClientInternalContact(clientDetails, employeeDetails));
        }

        // Debugging
        log.info("Size of clientInternalContactList list: {}", clientInternalContactList.size());
        log.info("Contents of clientInternalContactList list: {}", clientInternalContactList);

        // Convert List of ClientInternalContact objects to JSON
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            String jsonData = objectMapper.writeValueAsString(clientInternalContactList);
            fileWriter.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<ClientDetails> parseCsvToClientDetails(Reader reader) throws IOException {
        List<ClientDetails> clientDetailsList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            // Skip header if present
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(","); // Split CSV row into array
                ClientDetails clientDetails = new ClientDetails();
                clientDetails.setClientId(Long.parseLong(data[0]));
                clientDetails.setFirstName(data[1]);
                clientDetails.setMiddleName(data[2]);
                clientDetails.setFamilyName(data[3]);
                clientDetails.setEmail(data[4]);
                clientDetails.setRole(data[5]);
                clientDetails.setTelephoneNumber(data[6]);
                clientDetails.setBirthDate(data[7]);
                clientDetails.setRecordCreationDate(data[8]);
                clientDetails.setLastContactedDate(data[9]);
                clientDetails.setAccountNumber(data[10]);
                clientDetailsList.add(clientDetails);
            }
        }
        return clientDetailsList;
    }

    private List<ClientAddress> parseCsvToClientAddresses(Reader reader) throws IOException {
        List<ClientAddress> clientAddresses = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            // Skip header if present
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(","); // Split CSV row into array
                ClientAddress clientAddress = new ClientAddress();
                // Set ClientAddress fields based on CSV columns
                clientAddress.setClientAddressId(Long.parseLong(data[0]));
                clientAddress.setAddressHouseNumber(Long.parseLong(data[1]));
                clientAddress.setAddressLineOne(data[2]);
                clientAddress.setAddressLineTwo(data[3]);
                clientAddress.setCity(data[4]);
                clientAddress.setState(data[5]);
                clientAddress.setPostcode(data[6]);
                clientAddress.setCountry(data[7]);
                clientAddress.setAddressTelephone(data[8]);
                clientAddress.setAddressEmail(data[9]);
                clientAddress.setAddressUrl(data[10]);

                // Set PPOB address fields based on CSV columns
                clientAddress.setPpobAddressHouseNumber(Long.parseLong(data[11]));
                clientAddress.setPpobAddressLineOne(data[12]);
                clientAddress.setPpobAddressLineTwo(data[13]);
                clientAddress.setPpobCity(data[14]);
                clientAddress.setPpobState(data[15]);
                clientAddress.setPpobPostcode(data[16]);
                clientAddress.setPpobCountry(data[17]);
                clientAddress.setPpobAddressTelephone(data[18]);
                clientAddress.setPpobAddressEmail(data[19]);
                clientAddress.setPpobAddressUrl(data[20]);

                clientAddresses.add(clientAddress);
            }
        }
        return clientAddresses;
    }

    private List<EmployeeDetails> parseCsvToEmployeeDetails(Reader reader) throws IOException {
        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            // Skip header if present
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(","); // Split CSV row into array
                EmployeeDetails employeeDetails = new EmployeeDetails();
                // Set EmployeeDetails fields based on CSV columns
                employeeDetails.setEmployeeId(Long.parseLong(data[0]));
                employeeDetails.setFirstName(data[1]);
                employeeDetails.setMiddleName(data[2]);
                employeeDetails.setFamilyName(data[3]);
                employeeDetails.setEmail(data[4]);
                employeeDetails.setRole(data[5]);
                employeeDetails.setRoleId(Long.parseLong(data[6]));
                employeeDetails.setTelephoneNumber(data[7]);

                employeeDetailsList.add(employeeDetails);
            }
        }
        return employeeDetailsList;
    }

    private List<LegalDetails> parseCsvToLegalDetails(Reader reader) throws IOException {
        List<LegalDetails> legalDetailsList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            // Skip header if present
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(","); // Split CSV row into array
                LegalDetails legalDetails = new LegalDetails();
                // Set LegalDetails fields based on CSV columns
                legalDetails.setLegalDetailsId(Long.parseLong(data[0]));
                legalDetails.setLei(data[1]);
                legalDetails.setClientLegalName(data[2]);
                legalDetails.setClientName(data[3]);
                legalDetails.setRiskRating(data[4]);
                legalDetails.setNextReviewDate(data[5]);
                legalDetails.setClientStatus(data[6]);
                legalDetails.setRegulatorName(data[7]);
                legalDetails.setRegulatorId(data[8]);
                legalDetails.setCapacity(data[9]);
                legalDetails.setClientLegalClassification(data[10]);

                legalDetailsList.add(legalDetails);
            }
        }
        return legalDetailsList;
    }

    private List<TradeDetails> parseCsvToTradeDetails(Reader reader) throws IOException {
        List<TradeDetails> tradeDetailsList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            // Skip header if present
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(","); // Split CSV row into array
                TradeDetails tradeDetails = new TradeDetails();
                // Set TradeDetails fields based on CSV columns
                tradeDetails.setTradeDetailsId(Long.parseLong(data[0]));
                tradeDetails.setAccountNumber(data[1]);
                tradeDetails.setRevenue(Long.parseLong(data[2]));
                tradeDetails.setSubAccountNumber(data[3]);
                tradeDetails.setTimeOfExecution(data[4]);
                tradeDetails.setProductGrouping(data[5]);
                tradeDetails.setContractingEntity(data[6]);

                tradeDetailsList.add(tradeDetails);
            }
        }
        return tradeDetailsList;
    }
}
