package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.services.ClientTradeDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/client-trade-details")
@Api(value = "Client Trade Details", protocols = "http", tags = "Client Trade Details API")
public class ClientTradeDetailsController {
    private final ClientTradeDetailsService clientTradeDetailsService;

    @Autowired
    public ClientTradeDetailsController(ClientTradeDetailsService clientTradeDetailsService) {
        this.clientTradeDetailsService = clientTradeDetailsService;
    }

    @GetMapping("/all-clients")
    @ApiOperation(value = "Get All Clients", notes = "Retrieve a list of all client trade details.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of clients."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<ClientTradeDetails> getAllClients() {
        return clientTradeDetailsService.getAllClientTradeDetails();
    }

    @GetMapping("/trades/by-date/{productGrouping}")
    @ApiOperation(value = "Get Trades by Date and Product", notes = "Retrieve client trade details by product grouping and date range.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of trades."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<ClientTradeDetails>> getByProductAndDate(
            @PathVariable String productGrouping,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ClientTradeDetails> result = clientTradeDetailsService.getByTimeAndProduct(startDate, endDate, productGrouping);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/trades/by-revenue/{productGrouping}")
    @ApiOperation(value = "Get Trades by Revenue and Product", notes = "Retrieve all client trade details by revenue range and product grouping.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of trades."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<ClientTradeDetails>> getByRevenueAndProduct(
            @RequestParam Long minRevenue,
            @RequestParam Long maxRevenue,
            @PathVariable String productGrouping) {
        List<ClientTradeDetails> result = clientTradeDetailsService.getByRevenueAndProduct(minRevenue, maxRevenue, productGrouping);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/clients/{clientId}/trades/{productGrouping}")
    @ApiOperation(value = "Get Trades by Client ID and Product", notes = "Retrieve client trade details specific to a client and product grouping.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of trades."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<ClientTradeDetails>> getByClientIdAndProduct(
            @PathVariable Long clientId,
            @PathVariable String productGrouping) {
        List<ClientTradeDetails> result = clientTradeDetailsService.getByClientIdAndProduct(clientId, productGrouping);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/trades/by-date-and-clientId")
    @ApiOperation(value = "Get Trades by Client ID and Date", notes = "Retrieve client trade details  specific to a client and date range.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of trades."),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<ClientTradeDetails>> getByClientIdAndDate(
            @RequestParam Long clientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ClientTradeDetails> result = clientTradeDetailsService.getByClientIdAndTimeRange(clientId, startDate, endDate);
        return ResponseEntity.ok(result);
    }
}
