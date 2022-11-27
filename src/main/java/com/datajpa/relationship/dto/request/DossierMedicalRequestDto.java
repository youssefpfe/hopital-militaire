package com.datajpa.relationship.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DossierMedicalRequestDto {
    private String nomPatient;
    private String prenomPatient;
    private String consultationEffectuee;
    private String resultatPrestation;
    private Date postedAt;
    private Date lastUpdatedAt;
    private Long serviceconsultationId;
    private Long consultantId;
    private Long patientId;
}
