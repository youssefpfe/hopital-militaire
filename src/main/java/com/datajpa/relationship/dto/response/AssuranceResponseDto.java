package com.datajpa.relationship.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssuranceResponseDto {
    private Long id;
    private String nomPatient;
    private String prenomPatient;
    private String identitePatient;
    private Integer numAffilation;
    private String typePrestation;
    private Double montantConvention;
    private Date datePrestation;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date PostedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date LastUpdatedAt;
    private List<String> paiementNames;

}
