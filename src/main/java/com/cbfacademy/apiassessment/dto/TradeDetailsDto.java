package com.cbfacademy.apiassessment.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeDetailsDto {
    private Long tradeDetailsId;
    private String accountNumber;
    private Long revenue;
    private String subAccountNumber;
    private LocalDate timeOfExecution;
    private String productGrouping;
    private String contractingEntity;
}
