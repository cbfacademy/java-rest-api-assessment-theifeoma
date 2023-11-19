package com.cbfacademy.apiassessment.mappers;
import com.cbfacademy.apiassessment.dto.*;
import com.cbfacademy.apiassessment.entities.ClientAddress;
import com.cbfacademy.apiassessment.entities.TradeDetails;
import com.cbfacademy.apiassessment.entities.ClientDetails;
import com.cbfacademy.apiassessment.entities.EmployeeDetails;
import com.cbfacademy.apiassessment.entities.LegalDetails;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClientMapperTest {

    @Test
    public void MapToClientDto_ShouldMapCorrectly() {
        // Create sample ClientDetails and ClientAddress objects
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setRole("Sales");

        ClientAddress clientAddress = new ClientAddress();
        clientAddress.setCountry("Japan");

        // Perform the mapping
        ClientDto clientDto = ClientMapper.INSTANCE.mapToClientDto(clientDetails, clientAddress);

        // Verify the mapping results
        assertEquals(clientDetails.getRole(), clientDto.getRole());
        assertEquals(clientAddress.getCountry(), clientDto.getCountry());
    }

    @Test
    public void MapToClientDto_ShouldBeNull_WhenNullInputs() {
        // Test when clientDetails is null
        ClientDto clientDto1 = ClientMapper.INSTANCE.mapToClientDto(null, new ClientAddress());
        assertNull(clientDto1.getAddressLineOne());

        // Test when clientAddress is null
        ClientDto clientDto2 = ClientMapper.INSTANCE.mapToClientDto(new ClientDetails(), null);
        assertNull(clientDto2.getCountry());

        // Test when both clientDetails and clientAddress are null
        ClientDto clientDto3 = ClientMapper.INSTANCE.mapToClientDto(null, null);
        assertNull(clientDto3);
    }

    @Test
    public void MapToClientAddress_ShouldMapCorrectly() {
        // Create sample ClientDto object
        ClientDto clientDto = new ClientDto();
        clientDto.setAddressLineOne("3 Samson Road");
        clientDto.setPostcode("E4 3RW");

        // Perform the mapping
        ClientAddress clientAddress = ClientMapper.INSTANCE.maptoClientAddress(clientDto);

        // Verify the mapping results
        assertEquals(clientDto.getPostcode(), clientAddress.getPostcode());
        assertEquals(clientDto.getAddressLineOne(), clientAddress.getAddressLineOne());
    }

    @Test
    public void MapClientDetails_ShouldMapCorrectly() {
        // Create sample ClientDto object
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(1L);

        // Perform the mapping
        ClientDetails clientDetails = ClientMapper.INSTANCE.mapClientDetails(clientDto);

        // Verify the mapping results
        assertEquals(clientDto.getClientId(), clientDetails.getClientId());
    }

    @Test
    public void MapToClientTradeDetails_ShouldMapCorrectly() {
        // Create sample ClientDetails and TradeDetails objects
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientId(1L);

        TradeDetails tradeDetails = new TradeDetails();
        tradeDetails.setTimeOfExecution("2023-11-15");

        // Perform the mapping
        ClientTradeDetails clientTradeDetails = ClientMapper.INSTANCE.mapToClientTradeDetails(clientDetails, Arrays.asList(tradeDetails));

        // Verify the mapping results
        assertEquals(clientDetails.getClientId(), clientTradeDetails.getClientId());
        assertEquals(1, clientTradeDetails.getListOfClientTrades().size());

        // Assuming mapToTradeDetailsDto is correctly implemented
        assertEquals(
                LocalDate.parse(tradeDetails.getTimeOfExecution(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                clientTradeDetails.getListOfClientTrades().get(0).getTimeOfExecution()
        );
    }

    @Test
    public void MapToTradeDetailsDto_ShouldMapCorrectly() {
        // Create sample TradeDetails object
        TradeDetails tradeDetails = new TradeDetails();
        tradeDetails.setTimeOfExecution("2023-11-15");

        // Perform the mapping
        TradeDetailsDto tradeDetailsDto = ClientMapper.INSTANCE.mapToTradeDetailsDto(tradeDetails);

        // Verify the mapping results
        assertEquals(LocalDate.parse(tradeDetails.getTimeOfExecution(), DateTimeFormatter.ofPattern("yyyy-MM-dd")), tradeDetailsDto.getTimeOfExecution());
    }

    @Test
    public void MapToClientLegalDetails_ShouldMapCorrectly() {
        // Create sample ClientDetails and LegalDetails objects
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientId(1L);

        LegalDetails legalDetails = new LegalDetails();
        legalDetails.setClientName("ABC Corp");

        // Perform the mapping
        ClientLegalDetails clientLegalDetails = ClientMapper.INSTANCE.mapToClientLegalDetails(clientDetails, legalDetails);

        // Verify the mapping results
        assertEquals(clientDetails.getClientId(), clientLegalDetails.getClientId());
        assertEquals(legalDetails.getClientName(), clientLegalDetails.getClientName());
    }

    @Test
    public void MapToClientInternalContact_ShouldMapCorrectly() {
        // Create sample ClientDetails and EmployeeDetails objects
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientId(1L);

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setFirstName("John");

        // Perform the mapping
        ClientInternalContact clientInternalContact = ClientMapper.INSTANCE.mapToClientInternalContact(clientDetails, employeeDetails);

        // Verify the mapping results
        assertEquals(clientDetails.getClientId(), clientInternalContact.getClientId());
        assertEquals(employeeDetails.getFirstName(), clientInternalContact.getFirstName());
    }
}
