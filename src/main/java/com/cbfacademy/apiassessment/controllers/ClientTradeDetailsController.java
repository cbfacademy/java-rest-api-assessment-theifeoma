package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.services.ClientTradeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/client-trade-details")
public class ClientTradeDetailsController {
    private final ClientTradeDetailsService clientTradeDetailsService;

    @Autowired
    public ClientTradeDetailsController(ClientTradeDetailsService clientTradeDetailsService) {
        this.clientTradeDetailsService = clientTradeDetailsService;
    }

    @GetMapping("/all-clients")
    public List<ClientTradeDetails> getAllClients() {
        return clientTradeDetailsService.getAllClientTradeDetails();
    }

    // Endpoint for getting client trade details by product grouping and date range
    @GetMapping("/trades/by-date/{productGrouping}")
    public ResponseEntity<List<ClientTradeDetails>> getByProductAndDate(
            @PathVariable String productGrouping,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ClientTradeDetails> result = clientTradeDetailsService.getByTimeAndProduct(startDate, endDate, productGrouping);
        return ResponseEntity.ok(result);
    }

    // Endpoint for getting client trade details by revenue range and product grouping
    @GetMapping("/trades/by-revenue/{productGrouping}")
    public ResponseEntity<List<ClientTradeDetails>> getByRevenueAndProduct(
            @RequestParam Long minRevenue,
            @RequestParam Long maxRevenue,
            @PathVariable String productGrouping) {
        List<ClientTradeDetails> result = clientTradeDetailsService.getByRevenueAndProduct(minRevenue, maxRevenue, productGrouping);
        return ResponseEntity.ok(result);
    }

    // Endpoint for getting client trade details by client id and product grouping
    @GetMapping("/clients/{clientId}/trades/{productGrouping}")
    public ResponseEntity<List<ClientTradeDetails>> getByClientIdAndProduct(
            @PathVariable Long clientId,
            @PathVariable String productGrouping) {
        List<ClientTradeDetails> result = clientTradeDetailsService.getByClientIdAndProduct(clientId, productGrouping);
        return ResponseEntity.ok(result);
    }

    // Endpoint for getting client trade details by client id and date range
    @GetMapping("/trades/by-date-and-clientId")
    public ResponseEntity<List<ClientTradeDetails>> getByClientIdAndDate(
            @RequestParam Long clientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ClientTradeDetails> result = clientTradeDetailsService.getByClientIdAndTimeRange(clientId, startDate, endDate);
        return ResponseEntity.ok(result);
    }
}
