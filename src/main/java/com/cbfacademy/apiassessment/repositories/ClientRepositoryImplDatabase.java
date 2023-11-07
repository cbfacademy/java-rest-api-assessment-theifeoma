package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepositoryImplDatabase extends JpaRepository{
    void save(Client client);
    Client findById(Long id);
    void deleteById(Long id);

}
