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

import static com.cbfacademy.apiassessment.TestDataFactory.createClientDto;
import static com.cbfacademy.apiassessment.TestDataFactory.createListOfClientDto;
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
        List<ClientDto> expectedClients = createListOfClientDto();

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
        ClientDto newClient = createClientDto();

        List<ClientDto> existingClients = createListOfClientDto();

        when(clientDtoRepository.existsByClientId(newClient.getClientId())).thenReturn(false);
        when(clientDtoRepository.getAllDto()).thenReturn(existingClients);

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
        ClientDto existingClient =  createClientDto();

        when(clientDtoRepository.existsByClientId(existingClient.getClientId())).thenReturn(true);

        // Then and Assert
        assertThrows(IllegalStateException.class,
                () -> clientDetailsService.addNewClient(existingClient));

        // Verify
        verify(clientDtoRepository).existsByClientId(existingClient.getClientId());
    }
}
