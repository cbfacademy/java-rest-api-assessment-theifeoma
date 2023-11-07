package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.entities.Client;

public interface ClientRepository {
    void save(Client client);
    Client findById(Long id);
    void deleteById(Long id);
}
