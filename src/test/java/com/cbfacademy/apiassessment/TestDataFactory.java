package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.dto.ClientInternalContact;
import com.cbfacademy.apiassessment.dto.ClientLegalDetails;
import com.cbfacademy.apiassessment.dto.ClientTradeDetails;

import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    public TestDataFactory(){

    }

    public static ClientDto createClientDto(){
        ClientDto mockInstance = new ClientDto();

            // Set values for the main client details
            mockInstance.setClientId(3L);
            mockInstance.setFirstName("John");
            mockInstance.setMiddleName("Robert");
            mockInstance.setFamilyName("Doe");
            mockInstance.setEmail("john.doe@example.com");
            mockInstance.setRole("Customer");
            mockInstance.setTelephoneNumber("+44 20 1234 5678");
            mockInstance.setBirthDate("1985-03-15");
            mockInstance.setRecordCreationDate("2023-01-01");
            mockInstance.setLastContactedDate("2023-10-20");

            // Set values for address details
            mockInstance.setAddressHouseNumber(123L);
            mockInstance.setAddressLineOne("Street Address");
            mockInstance.setAddressLineTwo("Apt 42");
            mockInstance.setCity("London");
            mockInstance.setState("England");
            mockInstance.setPostcode("SW1A 1AA");
            mockInstance.setCountry("United Kingdom");
            mockInstance.setAddressTelephone("+44 20 9876 5432");
            mockInstance.setAddressEmail("john.doe.address@example.com");
            mockInstance.setAddressUrl("http://www.example.com");

            // Set values for PPOB (Primary Place of Business) address details
            mockInstance.setPpobAddressHouseNumber(456L);
            mockInstance.setPpobAddressLineOne("PPOB Street");
            mockInstance.setPpobAddressLineTwo("Suite 102");
            mockInstance.setPpobCity("Manchester");
            mockInstance.setPpobState("England");
            mockInstance.setPpobPostcode("M1 1AA");
            mockInstance.setPpobCountry("United Kingdom");
            mockInstance.setPpobAddressTelephone("+44 20 1234 5678");
            mockInstance.setPpobAddressEmail("john.doe.ppob@example.com");
            mockInstance.setPpobAddressUrl("http://www.ppob-example.com");

            return mockInstance;
    }

    public static List<ClientDto> createListOfClientDto(){
        List<ClientDto> mockInstances = new ArrayList<>();

        // First instance
        ClientDto mockInstance1 = new ClientDto();
        setMockValues(mockInstance1, 1L, "John", "Robert", "Doe", "john.doe@example.com", "Customer",
                "+44 20 1234 5678", "1985-03-15", "2023-01-01", "2023-10-20", 123L, "Street Address",
                "Apt 42", "London", "England", "SW1A 1AA", "United Kingdom", "+44 20 9876 5432",
                "john.doe.address@example.com", "http://www.example.com", 456L, "PPOB Street",
                "Suite 102", "Manchester", "England", "M1 1AA", "United Kingdom",
                "+44 20 1234 5678", "john.doe.ppob@example.com", "http://www.ppob-example.com");
        mockInstances.add(mockInstance1);

        // Second instance
        ClientDto mockInstance2 = new ClientDto();
        setMockValues(mockInstance2, 2L, "Jane", "Alice", "Smith", "jane.smith@example.com", "Manager",
                "+44 20 8765 4321", "1978-07-22", "2023-01-05", "2023-09-15", 456L, "Another Street",
                "Apt 12", "Manchester", "England", "M2 2BB", "United Kingdom", "+44 20 8765 4321",
                "jane.smith.address@example.com", "http://www.another-example.com", 789L,
                "Another PPOB Street", "Suite 203", "Birmingham", "England", "B1 1CC",
                "United Kingdom", "+44 20 3456 7890", "jane.smith.ppob@example.com",
                "http://www.another-ppob-example.com");
        mockInstances.add(mockInstance2);

        return mockInstances;
    }

    public static ClientInternalContact createClientInternalContact(){
        ClientInternalContact mockInstance = new ClientInternalContact();

        // Set mock values
        mockInstance.setClientId(1L);
        mockInstance.setEmployeeId(101L);
        mockInstance.setFirstName("Alice");
        mockInstance.setMiddleName("Mary");
        mockInstance.setFamilyName("Johnson");
        mockInstance.setEmail("alice.johnson@example.com");
        mockInstance.setRole("Internal Contact");
        mockInstance.setRoleId(301L);
        mockInstance.setTelephoneNumber("+44 20 1234 5678");

        return mockInstance;
    }

    public static ClientLegalDetails createClientLegalDetails(){
        ClientLegalDetails mockInstance = new ClientLegalDetails();

        // Set mock values
        mockInstance.setLegalDetailsId(1L);
        mockInstance.setClientId(101L);
        mockInstance.setLei("ABC123XYZ456");
        mockInstance.setClientLegalName("ABC Corp Legal");
        mockInstance.setClientName("ABC Corp");
        mockInstance.setRiskRating("Low");
        mockInstance.setNextReviewDate("2023-12-31");
        mockInstance.setClientStatus("Active");
        mockInstance.setRegulatorName("Financial Conduct Authority");
        mockInstance.setRegulatorId("FCA123");
        mockInstance.setCapacity("Principal");
        mockInstance.setClientLegalClassification("Bank");

        return mockInstance;
    }

    public static ClientTradeDetails createClientTradeDetails(){
        ClientTradeDetails mockInstance = new ClientTradeDetails();

        // Set mock values
//        mockInstance.setClientId(1L);
//        mockInstance.setTradeDetailsId(101L);
//        mockInstance.setAccountNumber("ABC123");
//        mockInstance.setRevenue(100000L);
//        mockInstance.setSubAccountNumber("SubABC");
//        mockInstance.setTimeOfExecution("2023-11-15T12:30:00");
//        mockInstance.setProductGrouping("Stock");
//        mockInstance.setContractingEntity("UBS London");

        return mockInstance;
    }

    private static void setMockValues(ClientDto clientDto, Long clientId, String firstName, String middleName,
                                      String familyName, String email, String role, String telephoneNumber, String birthDate,
                                      String recordCreationDate, String lastContactedDate, Long addressHouseNumber,
                                      String addressLineOne, String addressLineTwo, String city, String state, String postcode,
                                      String country, String addressTelephone, String addressEmail, String addressUrl,
                                      Long ppobAddressHouseNumber, String ppobAddressLineOne, String ppobAddressLineTwo,
                                      String ppobCity, String ppobState, String ppobPostcode, String ppobCountry,
                                      String ppobAddressTelephone, String ppobAddressEmail, String ppobAddressUrl) {

        clientDto.setClientId(clientId);
        clientDto.setFirstName(firstName);
        clientDto.setMiddleName(middleName);
        clientDto.setFamilyName(familyName);
        clientDto.setEmail(email);
        clientDto.setRole(role);
        clientDto.setTelephoneNumber(telephoneNumber);
        clientDto.setBirthDate(birthDate);
        clientDto.setRecordCreationDate(recordCreationDate);
        clientDto.setLastContactedDate(lastContactedDate);

        clientDto.setAddressHouseNumber(addressHouseNumber);
        clientDto.setAddressLineOne(addressLineOne);
        clientDto.setAddressLineTwo(addressLineTwo);
        clientDto.setCity(city);
        clientDto.setState(state);
        clientDto.setPostcode(postcode);
        clientDto.setCountry(country);
        clientDto.setAddressTelephone(addressTelephone);
        clientDto.setAddressEmail(addressEmail);
        clientDto.setAddressUrl(addressUrl);

        clientDto.setPpobAddressHouseNumber(ppobAddressHouseNumber);
        clientDto.setPpobAddressLineOne(ppobAddressLineOne);
        clientDto.setPpobAddressLineTwo(ppobAddressLineTwo);
        clientDto.setPpobCity(ppobCity);
        clientDto.setPpobState(ppobState);
        clientDto.setPpobPostcode(ppobPostcode);
        clientDto.setPpobCountry(ppobCountry);
        clientDto.setPpobAddressTelephone(ppobAddressTelephone);
        clientDto.setPpobAddressEmail(ppobAddressEmail);
        clientDto.setPpobAddressUrl(ppobAddressUrl);
    }
}
