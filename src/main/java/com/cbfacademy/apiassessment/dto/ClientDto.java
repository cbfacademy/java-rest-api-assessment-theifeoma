package com.cbfacademy.apiassessment.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
        private Long clientId;
        private String firstName;
        private String middleName;
        private String familyName;
        private String email;
        private String role;
        private String telephoneNumber;
        private String birthDate;
        private String recordCreationDate;
        private String lastContactedDate;

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
