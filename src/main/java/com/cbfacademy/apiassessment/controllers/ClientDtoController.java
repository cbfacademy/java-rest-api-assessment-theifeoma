package com.cbfacademy.apiassessment.controllers;

import com.cbfacademy.apiassessment.repositories.ClientDtoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

public class ClientDtoController {
    private final ClientDtoRepository clientDtoRepository = new ClientDtoRepository("data.json");

    @GetMapping("/data")
    public List<Object> getAllData() throws IOException {
        return clientDtoRepository.getAll();
    }

    @PostMapping("/data")
    public void createData(@RequestBody List<Object> newData) throws IOException {
        List<Object> existingData = clientDtoRepository.getAll();
        existingData.addAll(newData);
        clientDtoRepository.save(existingData);
    }

    // Implement other CRUD endpoints (update, delete) as needed
}
