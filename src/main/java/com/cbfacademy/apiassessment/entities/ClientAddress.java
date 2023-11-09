package com.cbfacademy.apiassessment.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientAddress {
    private Long clientAddressId;
    private Long addressHouseNumber;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String state;
    private String postcode;
    private String country;
    private String addressTelephone;
    private String addressEmail;
    private String addressUrl;

    private Long ppobAddressHouseNumber;
    private String ppobAddressLineOne;
    private String ppobAddressLineTwo;
    private String ppobCity;
    private String ppobState;
    private String ppobPostcode;
    private String ppobCountry;
    private String ppobAddressTelephone;
    private String ppobAddressEmail;
    private String ppobAddressUrl;
}
