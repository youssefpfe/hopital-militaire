package com.datajpa.relationship.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestationResponseDto {
    private Long id;
    private String naturePrestation;
    private Float coutPourAssure;
    private Float coutPourNonAssure;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date PostedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date LastUpdatedAt;
    private String facturationName;

}
