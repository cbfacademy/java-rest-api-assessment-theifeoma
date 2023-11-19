package com.cbfacademy.apiassessment.entities;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeDetails {
    private Long tradeDetailsId;
    private String accountNumber;
    private Long revenue;
    private String subAccountNumber;
    private String timeOfExecution;
    private String productGrouping;
    private String contractingEntity;
}
