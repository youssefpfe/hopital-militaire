package com.datajpa.relationship.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriseRDVRequestDto {
    private String nomPatient;
    private String prenomPatient;
    private Date dateRDV;
    private Date heureRDV;

    private String motif;
    private Boolean payed;
    private Date postedAt  ;
    private Date lastUpdatedAt ;
    private Long serviceconsultationId;
    private Long patientId;
    private Long consultantId;
    private Long examenId;
}
