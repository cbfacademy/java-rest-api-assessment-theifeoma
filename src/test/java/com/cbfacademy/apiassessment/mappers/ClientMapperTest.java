package com.cbfacademy.apiassessment.mappers;
import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.entities.ClientAddress;
import com.cbfacademy.apiassessment.entities.ClientDetails;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientMapperTest {

    private final ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

    @Test
    public void testMapToClientDto() {
        // Create sample ClientDetails and ClientAddress objects
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientId(1L);
        // Set other fields for clientDetails

        ClientAddress clientAddress = new ClientAddress();
        clientAddress.setClientAddressId(1L);
        // Set other fields for clientAddress

        // Perform the mapping
        ClientDto clientDto = ClientMapper.INSTANCE.mapToClientDto(clientDetails, clientAddress);

        // Verify the mapping results
        assertEquals(clientDetails.getClientId(), clientDto.getClientId());
        assertEquals(clientAddress.getClientAddressId(), clientDto.getAddressId());
        // Add assertions for other mapped fields in ClientDto
    }

    @Test
    public void testMapToClientAddress() {
        // Create sample ClientDto object
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(1L);
        clientDto.setAddressId(1L);
        // Set other fields for clientDto

        // Perform the mapping
        ClientAddress clientAddress = ClientMapper.INSTANCE.maptoClientAddress(clientDto);

        // Verify the mapping results
        assertEquals(clientDto.getAddressId(), clientAddress.getClientAddressId());
        // Add assertions for other mapped fields in ClientAddress
    }

    @Test
    public void testMapClientDetails() {
        // Create sample ClientDto object
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(1L);
        // Set other fields for clientDto

        // Perform the mapping
        ClientDetails clientDetails = ClientMapper.INSTANCE.mapClientDetails(clientDto);

        // Verify the mapping results
        assertEquals(clientDto.getClientId(), clientDetails.getClientId());
        // Add assertions for other mapped fields in ClientDetails
    }
}
