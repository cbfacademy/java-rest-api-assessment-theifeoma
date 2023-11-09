package com.cbfacademy.apiassessment.entities;


import lombok.Getter;
import lombok.Setter;

//consider instead handling exceptions when reading from csv
//instead of having this class
@Getter
@Setter
public class TradeDetails {
    private Long tradeDetailsId;
    private String accountNumber;
    private String timeOfExecution;
    private String product;
    private String contractingEntity;
}
