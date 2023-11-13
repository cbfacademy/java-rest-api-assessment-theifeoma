package com.cbfacademy.apiassessment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientTradeDetails {
    private Long tradeDetailsId;
    private Long clientId;
    private String firstName;
    private String middleName;
    private String familyName;
    private String telephoneNumber;
    private String email;
    private String accountNumber;
    private String product;
    private String contractingEntity;
}
