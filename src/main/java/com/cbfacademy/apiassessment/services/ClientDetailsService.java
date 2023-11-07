package com.cbfacademy.apiassessment.services;

import com.cbfacademy.apiassessment.entities.ClientDetails;
import com.cbfacademy.apiassessment.repositories.ClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientDetailsService {

    private final ClientDetailsRepository clientDetailsRepository;

    @Autowired
    public ClientDetailsService(ClientDetailsRepository clientDetailsRepository){
        this.clientDetailsRepository = clientDetailsRepository;
    }

    public List<ClientDetails> getAllClients(){
        return clientDetailsRepository.findAll();
    }

    public void addNewClient(ClientDetails client){
        Optional<ClientDetails> clientEmail = clientDetailsRepository.findClientDetailsByEmail(client.getEmail());
        if (clientEmail.isPresent()){
            throw new IllegalStateException("Email taken or is not original");
        }
        //save client
        clientDetailsRepository.save(client);
    }

    public void deleteClient(Long clientId){
        boolean clientExists = clientDetailsRepository.existsById(clientId);

        if(!clientExists){
            throw new IllegalStateException("Client with Client ID: " + clientId + " does not exist");
        }
        clientDetailsRepository.deleteById(clientId);
    }

    public List<ClientDetails> sortAllClientsByRole(String role){
        return clientDetailsRepository.findAllByRole(role);
    }
}
