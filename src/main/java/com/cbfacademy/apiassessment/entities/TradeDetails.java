package com.cbfacademy.apiassessment.entities;


import lombok.Getter;
import lombok.Setter;

//consider instead handling exceptions when reading from csv
//instead of having this class
@Getter
@Setter
public class TradeDetails {
    private Long id;
    private String timeOfRequest;
    private int sizeOfTrade;
    private int quantity;
    private double price;
    private String side;
}
