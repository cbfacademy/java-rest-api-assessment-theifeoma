package com.cbfacademy.apiassessment.services;

import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.repositories.ClientTradeDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClientTradeDetailsService {

    private final ClientTradeDetailsRepository clientTradeDetailsRepository;
    private static final Logger log = LoggerFactory.getLogger(ClientTradeDetailsService.class);

    @Autowired
    public ClientTradeDetailsService(ClientTradeDetailsRepository clientTradeDetailsRepository){
        this.clientTradeDetailsRepository = clientTradeDetailsRepository;
    }

    public List<ClientTradeDetails> getAllClientTradeDetails() throws IOException {
        return clientTradeDetailsRepository.getAll();
    }

}
