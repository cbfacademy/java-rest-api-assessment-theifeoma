package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

public class ClientRepositoryTest {
    private static final String CSV_FILE_PATH = "test.csv";

    @Mock
    private Csv<Client> clientCsv;

    @InjectMocks
    private ClientRepositoryImpl clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testSaveClientt() {
//        Client client = new Client(8L, "Hannah");
//
//        // Mock the behavior of CsvHelper
//        when(csvHelper.readAll()).thenReturn(List.of());
//        doAnswer(invocation -> {
//            List<Client> clients = invocation.getArgument(0);
//            clients.add(client);
//            return null;
//        }).when(csvHelper).writeToCsv(anyList(), any());
//
////        Client savedClient = clientRepository.save(client);
////        assertNotNull(savedClient);
////        assertEquals(client, savedClient);
//
//        // Verify that CsvHelper methods were called
////        verify(csvHelper, times(1)).readAll();
////        verify(csvHelper, times(1)).writeToCsv(anyList(), any());
//    }

//    @Test
//    public void testSaveClient() throws IOException {
//        // Arrange
//        ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
//        Client client = new Client(1L, "John Doe", "Jane Doe", "johndoe@example.com", "1234567890", "123 Main St");
//
//        // Stubbing the behavior of clientCsv
//        doNothing().when(clientCsv).addNewClientToClientCsv(client);
//
//        // Set the mocked clientCsv in the clientRepository
//        clientRepository.setClientCsv(clientCsv);
//
//        // Act
//        clientRepository.save(client);
//
//        // Assert
//        verify(clientCsv, times(1)).addNewClientToClientCsv(client);
//    }
}
