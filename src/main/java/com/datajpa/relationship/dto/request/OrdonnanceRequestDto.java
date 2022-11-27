package com.datajpa.relationship.dto.request;

import com.datajpa.relationship.model.Examen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdonnanceRequestDto {

    private Date dateOrdonnance;
    private String natureOrdonnance;
    private String nomPatient;
    private Date postedAt;
    private Date lastUpdatedAt ;
    private Long consultantId;
    private List<Long> examensId;
    private List<Long> medicamentsId;
    private Long consultationId;

}
