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
public class ClientDetailsService{

    private final ClientDtoRepository clientDtoRepository;
    private static final Logger log = LoggerFactory.getLogger(ClientDetailsService.class);

    @Autowired
    public ClientDetailsService(ClientDtoRepository clientDtoRepository){
        this.clientDtoRepository = clientDtoRepository;
    }

    public List<ClientDto> getAllDto() throws IOException{
        log.info("Inside Service Layer");
        return clientDtoRepository.getAllDto();
    }

    public void deleteClientD(Long clientId) throws IOException {
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
    }

    public void addNewClient(ClientDto client) throws IOException {
        boolean clientExists = clientDtoRepository.existsByClientId(client.getClientId());

        if (clientExists) {
            throw new IllegalStateException("Client with Client ID: " + client.getClientId() + " exists");
        }
        // Add the new client to the repository
        List<ClientDto> clients = clientDtoRepository.getAllDto();
        clients.add(client);
        clientDtoRepository.saveClientDto(clients);
    }

//    public List<ClientDetails> sortAllClientsByRole(String role) throws IOException {
//        List<ClientDetails> clients = clientDtoRepository.getAll();
//        List<ClientDetails> clientsWithRole = clients.stream()
//                .filter(client -> client != null && client.getRole().equalsIgnoreCase(role))
//                .collect(Collectors.toList());
//
//        if (clientsWithRole.isEmpty()) {
//            throw new IllegalStateException("No clients found with role: " + role);
//        }
//
//        // Sorting the clients with the specified role by their ID
//        clientsWithRole.sort(Comparator.comparing(ClientDetails::getClientId));
//        return clientsWithRole;
//    }

//    public void updateClient(Long clientId, ClientDetails updatedClient) throws IOException {
//        List<ClientDetails> clients = clientDtoRepository.getAll();
//
//        boolean clientExists = false;
//
//        for (Object client : clients) {
//            if (client instanceof ClientDetails && ((ClientDetails) client).getClientId().equals(clientId)) {
//                clients.remove(client);
//                clients.add(updatedClient);
//                clientExists = true;
//                break;
//            }
//        }
//
//        if (!clientExists) {
//            throw new IllegalStateException("Client with ID: " + clientId + " not found");
//        }
//
//        clientDtoRepository.save(clients);
//    }
}
