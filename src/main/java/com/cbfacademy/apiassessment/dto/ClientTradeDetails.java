package com.cbfacademy.apiassessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientTradeDetails {
    private Long clientId;
    private Long tradeDetailsId;
    private String accountNumber;
    private Long revenue;
    private String subAccountNumber;
    private String timeOfExecution;
    private String product;
    private String contractingEntity;
}
