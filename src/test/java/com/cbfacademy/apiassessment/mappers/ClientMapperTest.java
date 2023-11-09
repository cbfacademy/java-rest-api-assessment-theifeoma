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
        clientDetails.setClientId(1L);

        ClientAddress clientAddress = new ClientAddress();
        clientAddress.setClientAddressId(1L);

        // Perform the mapping
        ClientDto clientDto = ClientMapper.INSTANCE.mapToClientDto(clientDetails, clientAddress);

        // Verify the mapping results
        assertEquals(clientDetails.getClientId(), clientDto.getClientId());
        assertEquals(clientAddress.getClientAddressId(), clientDto.getAddressId());
    }

    @Test
    public void testMapToClientAddress() {
        // Create sample ClientDto object
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(1L);
        clientDto.setAddressId(1L);

        // Perform the mapping
        ClientAddress clientAddress = ClientMapper.INSTANCE.maptoClientAddress(clientDto);

        // Verify the mapping results
        assertEquals(clientDto.getAddressId(), clientAddress.getClientAddressId());
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
