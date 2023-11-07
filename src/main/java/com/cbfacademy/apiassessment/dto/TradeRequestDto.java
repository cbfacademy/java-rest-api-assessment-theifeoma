package com.cbfacademy.apiassessment.dto;

import com.cbfacademy.apiassessment.enums.Side;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//@Entity
@Getter
@Setter
public class TradeRequestDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timeOfRequest;
    private int sizeOfTrade;
    private int quantity;
    private double price;
    private Side side;

    //@ManyToOne
    private PortfolioDto portfolio;
}
