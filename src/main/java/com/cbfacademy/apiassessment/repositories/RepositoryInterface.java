package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.dto.ClientDto;

import java.util.List;

public interface RepositoryInterface<T> {
    public List<T> getAllDto();
}