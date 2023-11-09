package com.cbfacademy.apiassessment.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeDetails {
    private Long tradeDetailsId;
    private String accountNumber;
    private String timeOfExecution;
    private String product;
    private String contractingEntity;
}
