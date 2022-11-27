package com.datajpa.relationship.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturationRequestDto {
    private String nomPatient;
    private String codeFacture;
    private Double montantAPayer;
    private Double montantPaye;
    private String typePaiement;
    private Date dateFacture;
    private Double sommeRecue;
    private Double sommeRendue;
    private String nomAgent;
     private Long paiementId;
     private Long examenId;
     private Long priseRDVId;
}
