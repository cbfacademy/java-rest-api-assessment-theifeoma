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
    @JsonProperty("clientId")
    private Long clientId;
    @JsonProperty("tradeDetailsId")
    private Long tradeDetailsId;
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("revenue")
    private Long revenue;
    @JsonProperty("subAccountNumber")
    private String subAccountNumber;
    @JsonProperty("timeOfExecution")
    private String timeOfExecution;
    @JsonProperty("product")
    private String product;
    @JsonProperty("contractingEntity")
    private String contractingEntity;
}
