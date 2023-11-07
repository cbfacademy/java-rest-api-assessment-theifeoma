package com.cbfacademy.apiassessment.dto;

import com.cbfacademy.apiassessment.enums.Side;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TradeProcessedDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timeOfExecution;
    private int sizeOfTrade;
    private int quantityPassed;
    private int quantityNotFilled;
    private double priceOfTrade;
    private Side side;
    private boolean bestPrice;

    //@ManyToOne
    private TradeRequestDto tradeRequest;
}
