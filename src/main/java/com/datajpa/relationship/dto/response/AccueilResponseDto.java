package com.datajpa.relationship.dto.response;

import com.datajpa.relationship.model.PersonnelMedical;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccueilResponseDto {
    private Long id;
    private String nom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;

    private List<String> paiementNames;
    @JsonIgnoreProperties({"salleAttente","dossierMedicals","serviceConsultation","accueil"})
    List<PersonnelMedical> personnelMedicals;
}
