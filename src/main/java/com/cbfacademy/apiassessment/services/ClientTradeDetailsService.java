package com.cbfacademy.apiassessment.services;

import com.cbfacademy.apiassessment.dto.ClientLegalDetails;
import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.repositories.ClientTradeDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientTradeDetailsService {

    private final ClientTradeDetailsRepository clientTradeDetailsRepository;
    private static final Logger log = LoggerFactory.getLogger(ClientTradeDetailsService.class);

    @Autowired
    public ClientTradeDetailsService(ClientTradeDetailsRepository clientTradeDetailsRepository){
        this.clientTradeDetailsRepository = clientTradeDetailsRepository;
    }

    public List<ClientTradeDetails> getAllClientTradeDetails() {
        try {
            return clientTradeDetailsRepository.getAll();
        } catch (IOException e) {
            log.error("Error getting all client trade details: {}", e.getMessage());
            throw new RuntimeException("Failed to retrieve client trade details. Please try again later.");
        }
    }

    public List<ClientTradeDetails> getByProduct(String product) {
        try {
            List<ClientTradeDetails> clients = clientTradeDetailsRepository.getByProduct(product);

            if (clients.isEmpty()) {
                throw new NoSuchElementException("No clients found with product: " + product);
            }

            return clients;
        } catch (IOException e) {
            log.error("Error getting client trade details by product: {}", e.getMessage());
            throw new RuntimeException("Failed to retrieve client trade details by product. Please try again later.");
        }
    }

    public List<ClientTradeDetails> getByRevenueRange(Long minRevenue, Long maxRevenue) {
        try {
            List<ClientTradeDetails> clients = clientTradeDetailsRepository.getByRevenueRange(minRevenue, maxRevenue);

            if (clients.isEmpty()) {
                throw new NoSuchElementException("No clients found with revenue range: " + minRevenue + "and" + maxRevenue);
            }

            return clients;
        } catch (IOException e) {
            log.error("Error getting client trade details by revenue range: {}", e.getMessage());
            throw new RuntimeException("Failed to retrieve client trade details by revenue range. Please try again later.");
        }
    }

}
