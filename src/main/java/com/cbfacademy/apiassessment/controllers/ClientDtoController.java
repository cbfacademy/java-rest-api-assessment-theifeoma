package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.entities.ClientDetails;
import com.cbfacademy.apiassessment.repositories.ClientDtoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

//public class ClientDtoController {
//    private final ClientDtoRepository clientDtoRepository = new ClientDtoRepository("data.json");
//
//    @GetMapping("/data")
//    public List<ClientDetails> getAllData() throws IOException {
//        return clientDtoRepository.getAll();
//    }
//
////    @PostMapping("/data")
////    public void createData(@RequestBody List<Object> newData) throws IOException {
////        List<ClientDetails> existingData = clientDtoRepository.getAll();
////        existingData.addAll(newData);
////        clientDtoRepository.save(existingData);
//    }
//}
