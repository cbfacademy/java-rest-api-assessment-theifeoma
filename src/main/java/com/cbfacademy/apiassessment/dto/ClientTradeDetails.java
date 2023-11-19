package com.cbfacademy.apiassessment.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientTradeDetails {
    private Long clientId;
    private List<TradeDetailsDto> listOfClientTrades;
}
