package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.services.ClientTradeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    @GetMapping("/by-date/{productGrouping}")
    public ResponseEntity<List<ClientTradeDetails>> getByProductAndDate(
            @PathVariable String productGrouping,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ClientTradeDetails> result = clientTradeDetailsService.getByTimeAndProduct(startDate, endDate, productGrouping);
        return ResponseEntity.ok(result);
    }

    // Endpoint for getting client trade details by revenue range and product grouping
    @GetMapping("/by-revenue/{productGrouping}")
    public ResponseEntity<List<ClientTradeDetails>> getByRevenueAndProduct(
            @RequestParam Long minRevenue,
            @RequestParam Long maxRevenue,
            @PathVariable String productGrouping) {
        List<ClientTradeDetails> result = clientTradeDetailsService.getByRevenueAndProduct(minRevenue, maxRevenue, productGrouping);
        return ResponseEntity.ok(result);
    }

    // Endpoint for getting client trade details by client id and product grouping
    @GetMapping("/by/{clientId}/{productGrouping}")
    public ResponseEntity<List<ClientTradeDetails>> getByClientIdAndProduct(
            @PathVariable Long clientId,
            @PathVariable String productGrouping) {
        List<ClientTradeDetails> result = clientTradeDetailsService.getByClientIdAndProduct(clientId, productGrouping);
        return ResponseEntity.ok(result);
    }

    // Endpoint for getting client trade details by client id and date range
    @GetMapping("/by-date-and-clientId")
    public ResponseEntity<List<ClientTradeDetails>> getByClientIdAndDate(
            @RequestParam Long clientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ClientTradeDetails> result = clientTradeDetailsService.getByClientIdAndTimeRange(clientId, startDate, endDate);
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/by-product-grouping/{productGrouping}")
//    public List<ClientTradeDetails> getByProductGrouping(@PathVariable String productGrouping) {
//        return clientTradeDetailsService.getByProductGrouping(productGrouping);
//    }
//
//    @GetMapping("/by-revenue-range")
//    public List<ClientTradeDetails> getByRevenueRange(
//            @RequestParam Long minRevenue,
//            @RequestParam Long maxRevenue) {
//        return clientTradeDetailsService.getByRevenueRange(minRevenue, maxRevenue);
//    }
//
//    @GetMapping("/by-time-of-execution")
//    public ResponseEntity<List<ClientTradeDetails>> getByTimeOfExecutionRange(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//
//        try {
//            List<ClientTradeDetails> clients = clientTradeDetailsService.getByTimeOfExecutionRange(startDate, endDate);
//            return new ResponseEntity<>(clients, HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
