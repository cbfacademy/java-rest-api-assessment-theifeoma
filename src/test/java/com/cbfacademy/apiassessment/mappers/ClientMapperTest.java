package com.cbfacademy.apiassessment.mappers;
import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.entities.ClientAddress;
import com.cbfacademy.apiassessment.entities.ClientDetails;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientMapperTest {

    @Test
    public void testMapToClientDto() {
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
    public void testMapToClientAddress() {
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
    public void testMapClientDetails() {
        // Create sample ClientDto object
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(1L);

        // Perform the mapping
        ClientDetails clientDetails = ClientMapper.INSTANCE.mapClientDetails(clientDto);

        // Verify the mapping results
        assertEquals(clientDto.getClientId(), clientDetails.getClientId());
    }
}
