package com.cbfacademy.apiassessment.services;
import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.repositories.ClientDtoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientDetailsServiceTest {

    @Mock
    private ClientDtoRepository clientDtoRepository;

    @InjectMocks
    private ClientDetailsService clientDetailsService;

    @Test
    public void getAllDto_ShouldReturnListOfClients() throws IOException {
        // Given
        List<ClientDto> expectedClients = List.of(
                new ClientDto(
                        1L, "John", "Doe", "Smith", "john.doe@example.com", "Manager",
                        "123-456-7890", "1995-02-28", "2022-03-15", "2022-10-15", "Gold",
                        123L, "Main St", "Apt 7", "City1", "State1", "12345", "USA",
                        "123-456-7890", "john.doe@example.com", "www.example.com"
                ),
                new ClientDto(
                        2L, "Alice", "Eve", "Johnson", "alice.eve@example.com", "Employee",
                        "987-654-3210", "1988-09-10", "2022-04-20", "2022-09-20", "Silver",
                        789L, "Elm St", "Apartment 4", "City3", "State3", "67890", "USA",
                        "111-222-3333", "alice.eve@example.com", "www.another-example.com"
                )
        );

        when(clientDtoRepository.getAllDto()).thenReturn(expectedClients);

        // Then
        List<ClientDto> actualClients = clientDetailsService.getAllDto();

        // Assert
        verify(clientDtoRepository).getAllDto();
        assertEquals(expectedClients, actualClients);
    }

    @Test
    public void deleteClientD_WhenClientExists_ShouldDeleteClient() throws IOException {
        // Given
        long clientIdToDelete = 1L;

        when(clientDtoRepository.existsByClientId(clientIdToDelete)).thenReturn(true);

        // Then
        clientDetailsService.deleteClientD(clientIdToDelete);

        // Assert
        verify(clientDtoRepository).existsByClientId(clientIdToDelete);
        verify(clientDtoRepository).saveClientDto(Mockito.anyList());
    }

    @Test
    public void deleteClientD_WhenClientDoesNotExist_ShouldThrowException() throws IOException {
        // Given
        long clientIdToDelete = 1L;

        when(clientDtoRepository.existsByClientId(clientIdToDelete)).thenReturn(false);

        // Then and Assert
        assertThrows(IllegalStateException.class,
                () -> clientDetailsService.deleteClientD(clientIdToDelete));

        // Verify
        verify(clientDtoRepository).existsByClientId(clientIdToDelete);
    }

    @Test
    public void addNewClient_WhenClientDoesNotExist_ShouldAddClient() throws IOException {
        // Given
        ClientDto newClient =  new ClientDto(3L, "Jane", "B", "Doe", "jane.doe@example.com", "Manager", "987-654-3210",
                "1995-05-20", "2022-02-01", "2022-09-25", "Silver", 456L, "Oak St", "Apt 7", "City2", "State2", "56789", "USA", "987-654-3210", "jane.doe@example.com", "www.sample.com");

//        List<ClientDto> existingClients = List.of(
//                new ClientDto(
//                        1L, "John", "Doe", "Smith", "john.doe@example.com", "Manager",
//                        "123-456-7890", "1995-02-28", "2022-03-15", "2022-10-15", "Gold",
//                        123L, "Main St", "Apt 7", "City1", "State1", "12345", "USA",
//                        "123-456-7890", "john.doe@example.com", "www.example.com"
//                ),
//                new ClientDto(
//                        2L, "Alice", "Eve", "Johnson", "alice.eve@example.com", "Employee",
//                        "987-654-3210", "1988-09-10", "2022-04-20", "2022-09-20", "Silver",
//                        789L, "Elm St", "Apartment 4", "City3", "State3", "67890", "USA",
//                        "111-222-3333", "alice.eve@example.com", "www.another-example.com"
//                )
//        );

        when(clientDtoRepository.existsByClientId(newClient.getClientId())).thenReturn(false);
        //when(clientDtoRepository.getAllDto()).thenReturn(existingClients);

        // Then
        clientDetailsService.addNewClient(newClient);

        // Assert
        verify(clientDtoRepository).existsByClientId(newClient.getClientId());
        verify(clientDtoRepository).getAllDto();
        verify(clientDtoRepository).saveClientDto(Mockito.anyList());
    }

    @Test
    public void addNewClient_WhenClientExists_ShouldThrowException() throws IOException {
        // Given
        ClientDto existingClient =  new ClientDto(
                1L, "John", "Doe", "Smith", "john.doe@example.com", "Manager",
                "123-456-7890", "1995-02-28", "2022-03-15", "2022-10-15", "Gold",
                123L, "Main St", "Apt 7", "City1", "State1", "12345", "USA",
                "123-456-7890", "john.doe@example.com", "www.example.com"
        );

        when(clientDtoRepository.existsByClientId(existingClient.getClientId())).thenReturn(true);

        // Then and Assert
        assertThrows(IllegalStateException.class,
                () -> clientDetailsService.addNewClient(existingClient));

        // Verify
        verify(clientDtoRepository).existsByClientId(existingClient.getClientId());
    }
}
