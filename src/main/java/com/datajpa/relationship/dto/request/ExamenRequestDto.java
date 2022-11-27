package com.datajpa.relationship.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamenRequestDto {

    private String nomExamen;
    private Double prixExamen;
    private Date postedAt;
    private Date lastUpdatedAt;
    private Long ordonnanceId;
}
