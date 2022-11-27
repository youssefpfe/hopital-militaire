package com.datajpa.relationship.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.datajpa.relationship.dto.request.DetailsPatientRequestDto;
import com.datajpa.relationship.dto.response.DetailsPatientResponseDto;
import com.datajpa.relationship.service.DetailsPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DetailsPatientController.class})
@ExtendWith(SpringExtension.class)
class DetailsPatientControllerTest {
    @Autowired
    private DetailsPatientController detailsPatientController;

    @MockBean
    private DetailsPatientService detailsPatientService;

    /**
     * Method under test: {@link DetailsPatientController#addDetailsPatient(DetailsPatientRequestDto)}
     */
    @Test
    void testAddDetailsPatient() throws Exception {
        when(detailsPatientService.addDetailsPatient((DetailsPatientRequestDto) any()))
                .thenReturn(new DetailsPatientResponseDto());

        DetailsPatientRequestDto detailsPatientRequestDto = new DetailsPatientRequestDto();
        detailsPatientRequestDto.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatientRequestDto.setHeurePriseCharge(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatientRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatientRequestDto.setPatientId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatientRequestDto.setPostedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatientRequestDto.setServiceAffectation("Service Affectation");
        detailsPatientRequestDto.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(detailsPatientRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/detailsPatient/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(detailsPatientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"status\":null,\"serviceAffectation\":null,\"etatDuPatient\":null,\"heurePriseCharge\":null,"
                                        + "\"postedAt\":null,\"lastUpdatedAt\":null,\"patient\":null}"));
    }

    /**
     * Method under test: {@link DetailsPatientController#deleteSalleAttente(Long)}
     */
    @Test
    void testDeleteSalleAttente() throws Exception {
        when(detailsPatientService.deleteDetailsPatient((Long) any())).thenReturn(new DetailsPatientResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/detailsPatient/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(detailsPatientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"status\":null,\"serviceAffectation\":null,\"etatDuPatient\":null,\"heurePriseCharge\":null,"
                                        + "\"postedAt\":null,\"lastUpdatedAt\":null,\"patient\":null}"));
    }

    /**
     * Method under test: {@link DetailsPatientController#editSalleAttente(DetailsPatientRequestDto, Long)}
     */
    @Test
    void testEditSalleAttente() throws Exception {
        when(detailsPatientService.editDetailsPatient((Long) any(), (DetailsPatientRequestDto) any()))
                .thenReturn(new DetailsPatientResponseDto());

        DetailsPatientRequestDto detailsPatientRequestDto = new DetailsPatientRequestDto();
        detailsPatientRequestDto.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatientRequestDto.setHeurePriseCharge(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatientRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatientRequestDto.setPatientId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatientRequestDto.setPostedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatientRequestDto.setServiceAffectation("Service Affectation");
        detailsPatientRequestDto.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(detailsPatientRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/detailsPatient/edit/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(detailsPatientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"status\":null,\"serviceAffectation\":null,\"etatDuPatient\":null,\"heurePriseCharge\":null,"
                                        + "\"postedAt\":null,\"lastUpdatedAt\":null,\"patient\":null}"));
    }

    /**
     * Method under test: {@link DetailsPatientController#getSalleAttente(Long)}
     */
    @Test
    void testGetSalleAttente() throws Exception {
        when(detailsPatientService.getDetailsPatientById((Long) any())).thenReturn(new DetailsPatientResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/detailsPatient/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(detailsPatientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"status\":null,\"serviceAffectation\":null,\"etatDuPatient\":null,\"heurePriseCharge\":null,"
                                        + "\"postedAt\":null,\"lastUpdatedAt\":null,\"patient\":null}"));
    }

    /**
     * Method under test: {@link DetailsPatientController#getSalleAttentes()}
     */
    @Test
    void testGetSalleAttentes() throws Exception {
        when(detailsPatientService.getDetailsPatients()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/detailsPatient/getAll");
        MockMvcBuilders.standaloneSetup(detailsPatientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DetailsPatientController#getSalleAttentes()}
     */
    @Test
    void testGetSalleAttentes2() throws Exception {
        when(detailsPatientService.getDetailsPatients()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/detailsPatient/getAll");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(detailsPatientController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

