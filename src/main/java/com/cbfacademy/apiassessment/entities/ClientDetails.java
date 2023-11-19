package com.cbfacademy.apiassessment.entities;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDetails {
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
    private String accountNumber;
}
