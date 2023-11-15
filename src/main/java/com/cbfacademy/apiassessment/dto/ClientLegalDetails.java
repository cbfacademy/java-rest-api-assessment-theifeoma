package com.cbfacademy.apiassessment.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientLegalDetails {
    private Long legalDetailsId;
    private Long clientId;
    private String lei;
    private String clientLegalName;
    private String clientName;
    private String riskRating;
    private String nextReviewDate;
    private String clientStatus;
    private String regulatorName;
    private String regulatorId;
    private String capacity;
    private String clientLegalClassification;
}
