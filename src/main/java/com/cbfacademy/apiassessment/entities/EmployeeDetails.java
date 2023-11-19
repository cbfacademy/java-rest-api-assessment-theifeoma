package com.cbfacademy.apiassessment.entities;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDetails {
    private Long employeeId;
    private String firstName;
    private String middleName;
    private String familyName;
    private String email;
    private String role;
    private Long roleId;
    private String telephoneNumber;
}
