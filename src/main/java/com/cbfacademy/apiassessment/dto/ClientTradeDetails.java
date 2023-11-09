package com.cbfacademy.apiassessment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientTradeDetails {
    private Long id;
    private Long clientId;
    private String accountNumber;
    private String product;
    private String contractingEntity;
}
