package com.cbfacademy.apiassessment.services;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.dto.ClientLegalDetails;
import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.repositories.ClientLegalDetailsRepository;
import com.cbfacademy.apiassessment.repositories.ClientTradeDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientLegalDetailsService {

    private final ClientLegalDetailsRepository clientLegalDetailsRepository;
    private static final Logger log = LoggerFactory.getLogger(ClientLegalDetailsService.class);

    @Autowired
    public ClientLegalDetailsService(ClientLegalDetailsRepository clientLegalDetailsRepository) {
        this.clientLegalDetailsRepository = clientLegalDetailsRepository;
    }

    public List<ClientLegalDetails> getAllClientLegalDetails() {
        try {
            return clientLegalDetailsRepository.getAll();
        } catch (IOException e) {
            log.error("Error getting all client legal details: {}", e.getMessage());
            throw new RuntimeException("An error occurred while retrieving client legal details.");
        }
    }

    public List<ClientLegalDetails> findClientsByClassification(String classification) {
        try {
            List<ClientLegalDetails> clients = clientLegalDetailsRepository.findClientsByClassification(classification);

            if (clients.isEmpty()) {
                throw new NoSuchElementException("No clients found with classification: " + classification);
            }

            return clients;
        } catch (IOException e) {
            log.error("Error finding clients by classification: {}", e.getMessage());
            throw new RuntimeException("An error occurred while finding clients by classification.");
        }
    }

    public List<ClientLegalDetails> getAllClientsByStatus(String status) {
        try {
            List<ClientLegalDetails> clients = clientLegalDetailsRepository.getByStatus(status);

            if (clients.isEmpty()) {
                throw new NoSuchElementException("No clients found with status: " + status);
            }

            return clients;
        } catch (IOException e) {
            log.error("Error getting all clients by status: {}", e.getMessage());
            throw new RuntimeException("An error occurred while retrieving clients by status.");
        }
    }

    public List<ClientLegalDetails> getAllClientsByRiskRating(String riskRating) {
        try {
            List<ClientLegalDetails> clients = clientLegalDetailsRepository.getByRiskRating(riskRating);

            if (clients.isEmpty()) {
                throw new NoSuchElementException("No clients found with risk rating: " + riskRating);
            }

            return clients;
        } catch (IOException e) {
            log.error("Error getting all clients by risk rating: {}", e.getMessage());
            throw new RuntimeException("An error occurred while retrieving clients by risk rating.");
        }
    }
}
