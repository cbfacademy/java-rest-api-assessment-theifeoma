package com.cbfacademy.apiassessment.services;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.repositories.ClientDtoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClientDetailsService {

    private final ClientDtoRepository clientDtoRepository;
    private static final Logger log = LoggerFactory.getLogger(ClientDetailsService.class);

    @Autowired
    public ClientDetailsService(ClientDtoRepository clientDtoRepository) {
        this.clientDtoRepository = clientDtoRepository;
    }

    public List<ClientDto> getAllDto() {
        try {
            return clientDtoRepository.getAllDto();
        } catch (IOException e) {
            log.error("Error getting all clients: {}", e.getMessage());
            throw new RuntimeException("An error occurred while retrieving client data.");
        }
    }

    public ClientDto getClientById(Long clientId){
        try{
            boolean clientExists = clientDtoRepository.existsByClientId(clientId);

            if (!clientExists) {
                throw new IllegalStateException("Client with Client ID: " + clientId + " does not exist");
            }

            List<ClientDto> clients = clientDtoRepository.getAllDto();

            for (ClientDto client : clients) {
                if (client != null && client.getClientId().equals(clientId)) {
                    return client;
                }
            }

        }
        catch (IOException e){
            log.error("Error getting client: {}", e.getMessage());
            throw new RuntimeException("An error occurred while retrieving client details.");
        }
        return null; // Client not found
    }

    public void deleteClientD(Long clientId) {
        try {
            boolean clientExists = clientDtoRepository.existsByClientId(clientId);

            if (!clientExists) {
                throw new IllegalStateException("Client with Client ID: " + clientId + " does not exist");
            }

            // Remove the client by ID and save the updated data
            List<ClientDto> clients = clientDtoRepository.getAllDto();
            clients.removeIf(client -> {
                if (client != null) {
                    return client.getClientId().equals(clientId);
                }
                return false;
            });
            clientDtoRepository.saveClientDto(clients);
        } catch (IOException e) {
            log.error("Error deleting client: {}", e.getMessage());
            throw new RuntimeException("An error occurred while deleting client.");
        }
    }

    public void addNewClient(ClientDto client) {
        try {
            boolean clientExists = clientDtoRepository.existsByClientId(client.getClientId());

            if (clientExists) {
                throw new IllegalStateException("Client with Client ID: " + client.getClientId() + " exists");
            }

            // Add the new client to the repository
            List<ClientDto> clients = clientDtoRepository.getAllDto();
            clients.add(client);
            clientDtoRepository.saveClientDto(clients);
        } catch (IOException e) {
            log.error("Error adding new client: {}", e.getMessage());
            throw new RuntimeException("An error occurred while adding a new client.");
        }
    }

    public String updateClientEmail(Long clientId, String newEmail) {
        try {
            if (clientDtoRepository.updateClientEmail(clientId, newEmail)) {
                return "Client email updated successfully.";
            } else {
                return "Client not found. Email update failed.";
            }
        } catch (IOException e) {
            log.error("Error updating client email: {}", e.getMessage());
            throw new RuntimeException("An error occurred while updating client email.");
        }
    }
}
