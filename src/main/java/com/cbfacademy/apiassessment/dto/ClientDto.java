package com.cbfacademy.apiassessment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
        @JsonProperty("clientId")
        private Long clientId;
        @JsonProperty("firstName")
        private String firstName;
        @JsonProperty("middleName")
        private String middleName;
        @JsonProperty("familyName")
        private String familyName;
        @JsonProperty("email")
        private String email;
        @JsonProperty("role")
        private String role;
        @JsonProperty("telephoneNumber")
        private String telephoneNumber;
        @JsonProperty("birthDate")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private String birthDate;
        @JsonProperty("recordCreationDate")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private String recordCreationDate;
        @JsonProperty("lastContactedDate")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private String lastContactedDate;
        @JsonProperty("clientClassification")
        private String clientClassification;

        @JsonProperty("addressHouseNumber")
        private Long addressHouseNumber;
        @JsonProperty("addressLineOne")
        private String addressLineOne;
        @JsonProperty("addressLineTwo")
        private String addressLineTwo;
        @JsonProperty("city")
        private String city;
        @JsonProperty("state")
        private String state;
        @JsonProperty("postcode")
        private String postcode;
        @JsonProperty("country")
        private String country;
        @JsonProperty("addressTelephone")
        private String addressTelephone;
        @JsonProperty("addressEmail")
        private String addressEmail;
        @JsonProperty("addressUrl")
        private String addressUrl;
    }
