package com.datajpa.relationship.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssuranceRequestDto {
    private String nomPatient;
    private String prenomPatient;
    private String identitePatient;
    private Integer numAffilation;
    private String typePrestation;
    private Double montantConvention;
    private Date datePrestation;
    private Date PostedAt = new Date() ;
    private Date LastUpdatedAt = new Date();
}
