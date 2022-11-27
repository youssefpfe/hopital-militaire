package com.datajpa.relationship.dto.request;

import com.datajpa.relationship.model.SalleAttente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccueilRequestDto {
    private String nom;
    private Boolean nouveauPatient;



}
