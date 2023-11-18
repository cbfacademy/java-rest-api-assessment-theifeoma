package com.cbfacademy.apiassessment.controller;

import com.cbfacademy.apiassessment.controllers.ClientDtoController;
import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.services.ClientDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ClientDtoControllerTest {

    @Mock
    private ClientDetailsService clientDetailsService;

    @InjectMocks
    private ClientDtoController clientDtoController;

    private final MockMvc mockMvc;

    public ClientDtoControllerTest() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(clientDtoController).build();
    }

    @Test
    void getAllClients() throws Exception {
        when(clientDetailsService.getAllDto()).thenReturn(Arrays.asList(new ClientDto(), new ClientDto()));

        mockMvc.perform(get("/client-details/all-clients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));

        verify(clientDetailsService, times(1)).getAllDto();
        verifyNoMoreInteractions(clientDetailsService);
    }

    @Test
    void addNewClient() throws Exception {
        ClientDto clientDto = new ClientDto();

        mockMvc.perform(post("/client-details/add")
                        .content(asJsonString(clientDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clientDetailsService, times(1)).addNewClient(clientDto);
        verifyNoMoreInteractions(clientDetailsService);
    }

    @Test
    void deleteClient() throws Exception {
        long clientId = 1L;

        mockMvc.perform(delete("/client-details/delete/{clientId}", clientId))
                .andExpect(status().isOk());

        verify(clientDetailsService, times(1)).deleteClientD(clientId);
        verifyNoMoreInteractions(clientDetailsService);
    }

    @Test
    void updateClientEmail() throws Exception {
        long clientId = 1L;
        String newClientEmail = "new@example.com";

        when(clientDetailsService.updateClientEmail(clientId, newClientEmail)).thenReturn("Email updated successfully");

        mockMvc.perform(put("/client-details/update/{clientId}", clientId)
                        .param("newClientEmail", newClientEmail))
                .andExpect(status().isOk())
                .andExpect(content().string("Email updated successfully"));

        verify(clientDetailsService, times(1)).updateClientEmail(clientId, newClientEmail);
        verifyNoMoreInteractions(clientDetailsService);
    }

    @Test
    void getClientById() throws Exception {
        long clientId = 1L;
        ClientDto clientDto = new ClientDto();

        when(clientDetailsService.getClientById(clientId)).thenReturn(clientDto);

        mockMvc.perform(get("/client-details/get/{clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clientId").exists());

        verify(clientDetailsService, times(1)).getClientById(clientId);
        verifyNoMoreInteractions(clientDetailsService);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
