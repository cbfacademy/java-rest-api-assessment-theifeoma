package com.cbfacademy.apiassessment.repositories;
import com.cbfacademy.apiassessment.dto.ClientDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientDtoRepositoryTest {

    @Mock
    private ObjectMapper objectMapper;

    @Value("${json.file.clientDtoRepo.path}")
    private String jsonFilePath;

    @InjectMocks
    private ClientDtoRepository clientDtoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientDtoRepository = new ClientDtoRepository(jsonFilePath);
    }

    @Test
    void getAllDto_shouldReturnListOfClientDto() throws IOException {
        // Arrange
        String json = "[{\"clientId\":1,\"firstName\":\"John\"},{\"clientId\":2,\"firstName\":\"Alice\"}]";
        when(objectMapper.readValue(json, eq(new TypeReference<List<ClientDto>>() {}))).thenReturn(new ArrayList<>());

        // Act
        List<ClientDto> result = clientDtoRepository.getAllDto();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(objectMapper, times(1)).readValue(anyString(), eq(new TypeReference<List<ClientDto>>() {}));
    }

    @Test
    void getAllDto_shouldReturnNullIfNoClientDtoAvailable() throws IOException{

    }

    @Test
    void existsByClientId_shouldReturnTrueIfClientExists() throws IOException {
        // Arrange
        when(objectMapper.readValue(anyString(), eq(new TypeReference<List<ClientDto>>() {})))
                .thenReturn(List.of(new ClientDto(
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
                        )));

        // Act
        boolean result = clientDtoRepository.existsByClientId(1L);

        // Assert
        assertTrue(result);
    }

    @Test
    void existsByClientId_shouldReturnFalseIfClientDoesNotExist() throws IOException {
        // Arrange
        when(objectMapper.readValue(anyString(), eq(new TypeReference<List<ClientDto>>() {})))
                .thenReturn(List.of(new ClientDto(
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
                        )));

        // Act
        boolean result = clientDtoRepository.existsByClientId(3L);

        // Assert
        assertFalse(result);
    }

    @Test
    void saveClientDto_shouldWriteToFile() throws IOException {
        // Arrange
        List<ClientDto> clientDtoList = List.of(new ClientDto(4L, "Jane", "B", "Doe", "jane.doe@example.com", "Manager", "987-654-3210",
                "1995-05-20", "2022-02-01", "2022-09-25", "Silver", 456L, "Oak St", "Apt 7", "City2", "State2", "56789", "USA", "987-654-3210", "jane.doe@example.com", "www.sample.com"),new ClientDto(
                2L, "Alice", "Eve", "Johnson", "alice.eve@example.com", "Employee",
                "987-654-3210", "1988-09-10", "2022-04-20", "2022-09-20", "Silver",
                789L, "Elm St", "Apartment 4", "City3", "State3", "67890", "USA",
                "111-222-3333", "alice.eve@example.com", "www.another-example.com"
        ));

        // Act
        clientDtoRepository.saveClientDto(clientDtoList);

        // Assert
        verify(objectMapper, times(1)).writeValue(any(File.class), anyList());
    }
}
