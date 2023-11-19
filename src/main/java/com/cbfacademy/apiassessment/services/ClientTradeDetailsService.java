package com.cbfacademy.apiassessment.services;

import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.repositories.ClientTradeDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
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

    public List<ClientTradeDetails> getByTimeAndProduct(LocalDate startDate, LocalDate endDate, String productGrouping) {
        try {
            return clientTradeDetailsRepository.getByTimeAndProduct(startDate, endDate, productGrouping);
        } catch (IOException e) {
            log.error("Error getting client trade details by time and product grouping: {}", e.getMessage());
            throw new RuntimeException("Failed to retrieve client trade details. Please try again later.");
        }
    }

    public List<ClientTradeDetails> getByRevenueAndProduct(Long minRevenue, Long maxRevenue, String productGrouping) {
        try {
            return clientTradeDetailsRepository.getByRevenueAndProduct(minRevenue, maxRevenue, productGrouping);
        } catch (IOException e) {
            log.error("Error getting client trade details by revenue and product grouping: {}", e.getMessage());
            throw new RuntimeException("Failed to retrieve client trade details. Please try again later.");
        }
    }

    public List<ClientTradeDetails> getByClientIdAndProduct(Long clientId, String productGrouping) {
        try {
            return clientTradeDetailsRepository.getByClientIdAndProduct(clientId, productGrouping);
        } catch (IOException e) {
            log.error("Error getting client trade details by client id and product grouping: {}", e.getMessage());
            throw new RuntimeException("Failed to retrieve client trade details. Please try again later.");
        }
    }

    public List<ClientTradeDetails> getByClientIdAndTimeRange(Long clientId, LocalDate startDate, LocalDate endDate) {
        try {
            return clientTradeDetailsRepository.getByClientIdAndTimeRange(clientId, startDate, endDate);
        } catch (IOException e) {
            log.error("Error getting client trade details by client id and time range: {}", e.getMessage());
            throw new RuntimeException("Failed to retrieve client trade details. Please try again later.");
        }
    }

//    public List<ClientTradeDetails> getByProductGrouping(String productGrouping) {
//        try {
//            List<ClientTradeDetails> clients = clientTradeDetailsRepository.getByProductGrouping(productGrouping);
//
//            if (clients.isEmpty()) {
//                throw new NoSuchElementException("No clients found with product grouping: " + productGrouping);
//            }
//
//            return clients;
//        } catch (IOException e) {
//            log.error("Error getting client trade details by product grouping: {}", e.getMessage());
//            throw new RuntimeException("Failed to retrieve client trade details by product grouping. Please try again later.");
//        }
//    }
//
//    public List<ClientTradeDetails> getByRevenueRange(Long minRevenue, Long maxRevenue) {
//        try {
//            List<ClientTradeDetails> clients = clientTradeDetailsRepository.getByRevenueRange(minRevenue, maxRevenue);
//
//            if (clients.isEmpty()) {
//                throw new NoSuchElementException("No clients found with revenue range: " + minRevenue + "and" + maxRevenue);
//            }
//
//            return clients;
//        } catch (IOException e) {
//            log.error("Error getting client trade details by revenue range: {}", e.getMessage());
//            throw new RuntimeException("Failed to retrieve client trade details by revenue range. Please try again later.");
//        }
//    }
//
//    public List<ClientTradeDetails> getByTimeOfExecutionRange(LocalDate startDate, LocalDate endDate) {
//        try {
//            List<ClientTradeDetails> clients = clientTradeDetailsRepository.getByTimeOfExecutionRange(startDate, endDate);
//
//            if (clients.isEmpty()) {
//                throw new NoSuchElementException("No clients found with timeOfExecution range: " + startDate + " and " + endDate);
//            }
//
//            return clients;
//        } catch (IOException e) {
//            log.error("Error getting client trade details by timeOfExecution range: {}", e.getMessage());
//            throw new RuntimeException("Failed to retrieve client trade details by timeOfExecution range. Please try again later.");
//        }
//    }

}
