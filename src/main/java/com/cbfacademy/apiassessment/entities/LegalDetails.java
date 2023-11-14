package com.cbfacademy.apiassessment.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LegalDetails {
    private Long legalDetailsId;
    private String lei;
    private String clientLegalName;
    private String clientName;
    private String riskRating;
    private String nextReviewDate;
    private String clientStatus;
    private String regulatorName;
    private String regulatorId;
    private String capacity;
    private String clientClassification;
}
