package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.services.ClientTradeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public List<ClientTradeDetails> getAllClients() throws IOException {
        return clientTradeDetailsService.getAllClientTradeDetails();
    }

    @GetMapping("/by-product/{product}")
    public List<ClientTradeDetails> getByProduct(@PathVariable String product) throws IOException {
        return clientTradeDetailsService.getByProduct(product);
    }

    @GetMapping("/by-revenue-range")
    public List<ClientTradeDetails> getByRevenueRange(
            @RequestParam Long minRevenue,
            @RequestParam Long maxRevenue) throws IOException {
        return clientTradeDetailsService.getByRevenueRange(minRevenue, maxRevenue);
    }
}
