package com.cbfacademy.apiassessment.controller;

import com.cbfacademy.apiassessment.controllers.ClientDtoController;
import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.services.ClientDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.cbfacademy.apiassessment.TestDataFactory.createClientDto;
import static com.cbfacademy.apiassessment.TestDataFactory.createListOfClientDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ClientDtoControllerTest {

    @MockBean
    private ClientDetailsService clientDetailsService;


    private ClientDtoController clientDtoController;

    @BeforeEach
    void setUp() {
        clientDtoController = new ClientDtoController(clientDetailsService);
    }

    @Test
    void getAllClients_ShouldReturnListOfClients() {
        //When
        // Mocking behavior to return a list of ClientDto when getAllDto is called
        List<ClientDto> clients = createListOfClientDto();
        when(clientDetailsService.getAllDto()).thenReturn(clients);

        //Then
        ResponseEntity<List<ClientDto>> responseEntity = clientDtoController.getAllClients();

        // Verify the response status and content
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals(clients, responseEntity.getBody());
        verify(clientDetailsService).getAllDto();
        verify(clientDetailsService, times(1)).getAllDto();
    }

    @Test
    void getClientById_ShouldReturnClientDetails() {
        //When
        Long clientIdToRetrieve = 3L;
        ClientDto client = createClientDto();

        // Mocking behavior to return a ClientDto when getClientById is called
        when(clientDetailsService.getClientById(clientIdToRetrieve)).thenReturn(client);

        //Then
        ResponseEntity<ClientDto> responseEntity = clientDtoController.getClientById(clientIdToRetrieve);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(createClientDto().toString(), responseEntity.getBody().toString());

        verify(clientDetailsService, times(1)).getClientById(clientIdToRetrieve);
    }


    @Test
    void getAllClients_ShouldThrowError_WhenServiceLayerHasRuntimeExceptionError() {
        //When
        // Mocking behavior to simulate an exception in getAllClients
        when(clientDetailsService.getAllDto()).thenThrow(new RuntimeException("Simulated error"));

        //Then
        ResponseEntity<List<ClientDto>> responseEntity = clientDtoController.getAllClients();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        // Assert that the returned list is null
        assertNull(responseEntity.getBody());
        verify(clientDetailsService).getAllDto();
    }

    //works
    @Test
    void addNewClient_ShouldAddNewClient_WhenClientDoesNotExist() {
        //When
        ClientDto newClient = createClientDto();

        //Then
        ResponseEntity<Void> responseEntity = clientDtoController.addNewClient(newClient);

        // Assert that the response status is CREATED
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        verify(clientDetailsService).addNewClient(newClient);
    }

    //works
    @Test
    void deleteClient_ShouldRemoveClientSuccessfully() {
        //When
        Long clientIdToDelete = 1L;

        //Then
        ResponseEntity<Void> responseEntity = clientDtoController.deleteClient(clientIdToDelete);

        // Assert that the response status is NO_CONTENT
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(clientDetailsService).deleteClientD(clientIdToDelete);
    }

    @Test
    void updateClientEmail_ShouldReplaceExistingEmail() {
        //When
        Long clientIdToUpdate = 1L;
        String newEmail = "new.email@example.com";

        //Then
        ResponseEntity<String> responseEntity = clientDtoController.updateClientEmail(clientIdToUpdate, newEmail);

        // Assert that the response status is OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Ensure that the clientDetailsService.updateClientEmail method was called
        verify(clientDetailsService).updateClientEmail(clientIdToUpdate, newEmail);
    }
}
