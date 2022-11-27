package com.datajpa.relationship.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaiementRequestDto {

    private Integer codeFacture;

    private  String nomAssurance;
    private Double tauxCouverture;

    private String prestation;

    private Double montantPrestation;
    private Date datePaiement;
    private Date PostedAt = new Date() ;
    private Date LastUpdatedAt = new Date();
    private Long accueilId;
    private Long assuranceId;
}
