package com.cbfacademy.apiassessment.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientInternalContact {

    private Long clientId;
    private Long employeeId;
    private String firstName;
    private String middleName;
    private String familyName;
    private String email;
    private String role;
    private Long roleId;
    private String telephoneNumber;
}
