package com.cbfacademy.apiassessment.services;

import com.cbfacademy.apiassessment.entities.ClientDetails;
import com.cbfacademy.apiassessment.repositories.ClientDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientDetailsService{

    private final ClientDtoRepository clientDtoRepository;

    @Autowired
    public ClientDetailsService(ClientDtoRepository clientDtoRepository){
        this.clientDtoRepository = clientDtoRepository;
    }

    public List<ClientDetails> getAllClients() throws IOException {
        List<Object> clients = clientDtoRepository.getAll();
        return clients.stream()
                .filter(client -> client instanceof ClientDetails)
                .map(client -> (ClientDetails) client)
                .collect(Collectors.toList());
    }


    public void addNewClient(ClientDetails client) throws IOException {
        ClientDetails existingClient = (ClientDetails) clientDtoRepository.findClientDetailsByEmail(client.getEmail());
        if (existingClient != null) {
            throw new IllegalStateException("Email is already taken or is not original");
        }
        // Add the new client to the repository
        List<Object> clients = clientDtoRepository.getAll();
        clients.add(client);
        clientDtoRepository.save(clients);
    }


    public void deleteClient(Long clientId) throws IOException {
        boolean clientExists = clientDtoRepository.existsById(clientId);

        if (!clientExists) {
            throw new IllegalStateException("Client with Client ID: " + clientId + " does not exist");
        }

        // Remove the client by ID and save the updated data
        List<Object> clients = clientDtoRepository.getAll();
        clients.removeIf(client -> {
            if (client instanceof ClientDetails) {
                return ((ClientDetails) client).getClientId().equals(clientId);
            }
            return false;
        });
        clientDtoRepository.save(clients);
    }

    public List<ClientDetails> sortAllClientsByRole(String role) throws IOException {
        List<Object> clients = clientDtoRepository.getAll();
        List<ClientDetails> clientsWithRole = clients.stream()
                .filter(client -> client instanceof ClientDetails && ((ClientDetails) client).getRole().equalsIgnoreCase(role))
                .map(client -> (ClientDetails) client)
                .collect(Collectors.toList());

        if (clientsWithRole.isEmpty()) {
            throw new IllegalStateException("No clients found with role: " + role);
        }

        // Sorting the clients with the specified role by their ID
        clientsWithRole.sort(Comparator.comparing(ClientDetails::getClientId));
        return clientsWithRole;
    }

    public void updateClient(Long clientId, ClientDetails updatedClient) throws IOException {
        List<Object> clients = clientDtoRepository.getAll();

        boolean clientExists = false;

        for (Object client : clients) {
            if (client instanceof ClientDetails && ((ClientDetails) client).getClientId().equals(clientId)) {
                clients.remove(client);
                clients.add(updatedClient);
                clientExists = true;
                break;
            }
        }

        if (!clientExists) {
            throw new IllegalStateException("Client with ID: " + clientId + " not found");
        }

        clientDtoRepository.save(clients);
    }
}
