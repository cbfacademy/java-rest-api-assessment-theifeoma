package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.entities.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Long> {

    Optional<ClientDetails> findClientDetailsByEmail(String email);

    Optional<ClientDetails> findClientDetailsByClientId(Long id);

    List<ClientDetails> findAllByRole(String role);

    ClientDetails findByRole(String role);
}
