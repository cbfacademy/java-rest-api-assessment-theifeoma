package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.cbfacademy.apiassessment.TestDataFactory.createListOfClientDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientDtoRepositoryTest {

    @Mock
    private File jsonFile;

    @Mock
    @Value("${json.file.clientDtoRepo.path}")
    private String jsonFilePath;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ClientDtoRepository clientDtoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        clientDtoRepository = new ClientDtoRepository(jsonFilePath);
    }

    @Test
    void getAllDto() throws IOException {
        List<ClientDto> expectedClients = createListOfClientDto();

        when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(expectedClients);

        List<ClientDto> actualClients = clientDtoRepository.getAllDto();

        assertEquals(expectedClients, actualClients);
    }

    @Test
    void existsByClientId() throws IOException {
        List<ClientDto> clients = createListOfClientDto();

        when(clientDtoRepository.getAllDto()).thenReturn(clients);

        assertTrue(clientDtoRepository.existsByClientId(1L));
        assertFalse(clientDtoRepository.existsByClientId(3L));
    }

    @Test
    void binarySearchByClientId() {
        List<ClientDto> clients = createListOfClientDto();
//
//        assertEquals(0, clientDtoRepository.binarySearchByClientId(clients, 1L));
//        assertEquals(1, clientDtoRepository.binarySearchByClientId(clients, 2L));
//        assertEquals(-1, clientDtoRepository.binarySearchByClientId(clients, 3L));
    }

    @Test
    void saveClientDto() throws IOException {
        List<ClientDto> clients = createListOfClientDto();

        clientDtoRepository.saveClientDto(clients);

        verify(objectMapper, times(1)).writeValue(any(File.class), eq(clients));
    }

    @Test
    void updateClientEmail() throws IOException {
        List<ClientDto> clients = createListOfClientDto();

        when(clientDtoRepository.getAllDto()).thenReturn(clients);

        assertTrue(clientDtoRepository.updateClientEmail(1L, "new.email@example.com"));
        assertFalse(clientDtoRepository.updateClientEmail(3L, "new.email@example.com"));

        verify(objectMapper, times(1)).writeValue(any(File.class), eq(clients));
    }
}
