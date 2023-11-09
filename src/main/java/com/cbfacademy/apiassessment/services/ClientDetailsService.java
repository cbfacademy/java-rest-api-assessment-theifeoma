package com.cbfacademy.apiassessment.services;

import com.cbfacademy.apiassessment.entities.ClientDetails;
import com.cbfacademy.apiassessment.repositories.ClientDtoRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientDetailsService{

    private final ClientDtoRepository clientDtoRepository;

    public ClientDetailsService(ClientDtoRepository clientDtoRepository){
        this.clientDtoRepository = clientDtoRepository;
    }

    public List<Object> getAllClients() throws IOException {
        return clientDtoRepository.getAll();
    }

    public void addNewClient(ClientDetails client){
        Optional<ClientDetails> clientEmail = clientDtoRepository.findClientDetailsByEmail(client.getEmail());
        if (clientEmail.isPresent()){
            throw new IllegalStateException("Email taken or is not original");
        }
        //save client
        clientDtoRepository.save(client);
    }

    public void deleteClient(Long clientId){
        boolean clientExists = clientDtoRepository.existsById(clientId);

        if(!clientExists){
            throw new IllegalStateException("Client with Client ID: " + clientId + " does not exist");
        }
        clientDtoRepository.deleteById(clientId);
    }

    public List<ClientDetails> sortAllClientsByRole(String role){
        return clientDtoRepository.findAllByRole(role);
    }
}
