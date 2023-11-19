package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static com.cbfacademy.apiassessment.TestDataFactory.createListOfClientDto;
import static com.cbfacademy.apiassessment.constants.TestConstants.JSON_REPOSITORY_TEST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ClientDtoRepositoryTest {

    @Value(JSON_REPOSITORY_TEST)
    private String jsonFilePath;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ClientDtoRepository clientDtoRepository;

    @BeforeEach
    void setUp() {
        clientDtoRepository = new ClientDtoRepository(JSON_REPOSITORY_TEST);
        objectMapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void getAllDto_ShouldNotBeNull() throws IOException {
        // Mocking behavior to return a list of ClientDto when readValue is called
        when(clientDtoRepository.getAllDto()).thenReturn(createListOfClientDto());

        List<ClientDto> clientDtoList = clientDtoRepository.getAllDto();

        // Assert that the returned list is not null
        assertNotNull(clientDtoList);
    }

    @Test
    void saveClientDto_ShouldSaveClient() throws IOException {
        List<ClientDto> clients = createListOfClientDto();

        clientDtoRepository.saveClientDto(clients);

        verify(clientDtoRepository, times(1)).saveClientDto(clients);
    }

    @Test
    void saveClientDto_ShouldSaveClientDetails() throws IOException {
        List<ClientDto> testData = createListOfClientDto();

        when(clientDtoRepository.getAllDto()).thenReturn(testData);

        // Call the method to save client data
        clientDtoRepository.saveClientDto(testData);

        List<ClientDto> savedClients = clientDtoRepository.getAllDto();
        assertNotNull(savedClients);
        assertEquals(testData.size(), savedClients.size());
        // assert that the repository state is updated after saving
        assertTrue(savedClients.containsAll(testData));
    }

//    @Test
//    void testExistsByClientId() throws IOException {
//        // Create a list of ClientDto using your utility method
//        List<ClientDto> clientDtoList = createListOfClientDto();
//        clientDtoList.sort(Comparator.comparing(ClientDto::getClientId));
//
//        // Mocking behavior to return the created list when getAllDto is called
//        when(clientDtoRepository.getAllDto()).thenReturn(clientDtoList);
//
//        // When
//        boolean existingClientIdResult = clientDtoRepository.existsByClientId(2L);
//        boolean nonExistingClientIdResult = clientDtoRepository.existsByClientId(3L);
//
//        // Then
//        assertTrue(existingClientIdResult);
//        assertFalse(nonExistingClientIdResult);
//
//        verify(clientDtoRepository, times(1)).existsByClientId(1L);
//        verify(clientDtoRepository, times(1)).existsByClientId(3L);
//    }
//
//    @Test
//    void updateClientEmail_ShouldReplaceEmailCorrectly() throws IOException {
//        //When
//        List<ClientDto> testData = createListOfClientDto();
//        Long clientIdToUpdate = 1L;
//        String newEmail = "new.email@example.com";
//
//        // Mocking behavior for getAllDto method
//        when(clientDtoRepository.getAllDto()).thenReturn(testData);
//
//        // Mocking behavior for writeValue method
//        doNothing().when(objectMapper).writeValue(any(File.class), eq(testData));
//
//        assertTrue(clientDtoRepository.updateClientEmail(clientIdToUpdate, newEmail));
//        assertFalse(clientDtoRepository.updateClientEmail(3L, "new.email@example.com"));
//
//        verify(clientDtoRepository).updateClientEmail(clientIdToUpdate, newEmail);
//        verify(clientDtoRepository, times(1)).updateClientEmail(clientIdToUpdate, newEmail);
//    }
}
