package com.datajpa.relationship.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestationRequestDto {

    private String naturePrestation;
    private Float coutPourAssure;
    private Float coutPourNonAssure;
    private Date PostedAt = new Date() ;
    private Date LastUpdatedAt = new Date();
    private Long facturationId;
}
