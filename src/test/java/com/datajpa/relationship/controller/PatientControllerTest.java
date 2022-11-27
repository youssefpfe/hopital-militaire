package com.datajpa.relationship.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.datajpa.relationship.dto.request.PatientRequestDto;
import com.datajpa.relationship.dto.response.PatientResponseDto;
import com.datajpa.relationship.service.PatientService;
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

@ContextConfiguration(classes = {PatientController.class})
@ExtendWith(SpringExtension.class)
class PatientControllerTest {
    @Autowired
    private PatientController patientController;

    @MockBean
    private PatientService patientService;

    /**
     * Method under test: {@link PatientController#addPatient(PatientRequestDto)}
     */
    @Test
    void testAddPatient() throws Exception {
        when(patientService.addPatient((PatientRequestDto) any())).thenReturn(new PatientResponseDto());

        PatientRequestDto patientRequestDto = new PatientRequestDto();
        patientRequestDto.setAssurance("Assurance");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        patientRequestDto.setDateNaissance(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        patientRequestDto.setDetailPatientId(123L);
        patientRequestDto.setDomicile("Domicile");
        patientRequestDto.setEmail("jane.doe@example.org");
        patientRequestDto.setEstAssure(true);
        patientRequestDto.setGenre("Genre");
        patientRequestDto.setNom("Nom");
        patientRequestDto.setNumAffeliation("Num Affeliation");
        patientRequestDto.setNumIdendite(3);
        patientRequestDto.setPaiementId(123L);
        patientRequestDto.setPhoto("alice.liddell@example.org");
        patientRequestDto.setPrenom("Prenom");
        patientRequestDto.setSalleAttenteId(123L);
        patientRequestDto.setSeviceConsultationId(123L);
        patientRequestDto.setTauxAssurance(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(patientRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/Patient/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"nom\":null,\"prenom\":null,\"genre\":null,\"dateNaissance\":null,\"numIdendite\":null,\"photo\":null"
                                        + ",\"domicile\":null,\"estAssure\":null,\"assurance\":null,\"tauxAssurance\":null,\"email\":null,\"numAffeliation"
                                        + "\":null,\"postedAt\":null,\"lastUpdatedAt\":null,\"dossierMedical\":null,\"priseRDVs\":null,\"paiement\":null,"
                                        + "\"salleAttente\":null,\"detailsPatient\":null}"));
    }

    /**
     * Method under test: {@link PatientController#getPatients()}
     */
    @Test
    void testGetPatients() throws Exception {
        when(patientService.getPatients()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Patient/getAll");
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PatientController#getPatients()}
     */
    @Test
    void testGetPatients2() throws Exception {
        when(patientService.getPatients()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/Patient/getAll");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PatientController#CountAssuree()}
     */
    @Test
    void testCountAssuree() throws Exception {
        when(patientService.CountAssuree()).thenReturn(3L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Patient/getCount");
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    /**
     * Method under test: {@link PatientController#CountAssuree()}
     */
    @Test
    void testCountAssuree2() throws Exception {
        when(patientService.CountAssuree()).thenReturn(3L);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/Patient/getCount");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    /**
     * Method under test: {@link PatientController#deletePatient(Long)}
     */
    @Test
    void testDeletePatient() throws Exception {
        when(patientService.deletePatient((Long) any())).thenReturn(new PatientResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/Patient/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"nom\":null,\"prenom\":null,\"genre\":null,\"dateNaissance\":null,\"numIdendite\":null,\"photo\":null"
                                        + ",\"domicile\":null,\"estAssure\":null,\"assurance\":null,\"tauxAssurance\":null,\"email\":null,\"numAffeliation"
                                        + "\":null,\"postedAt\":null,\"lastUpdatedAt\":null,\"dossierMedical\":null,\"priseRDVs\":null,\"paiement\":null,"
                                        + "\"salleAttente\":null,\"detailsPatient\":null}"));
    }

    /**
     * Method under test: {@link PatientController#getPatient(Long)}
     */
    @Test
    void testGetPatient() throws Exception {
        when(patientService.getPatientById((Long) any())).thenReturn(new PatientResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Patient/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"nom\":null,\"prenom\":null,\"genre\":null,\"dateNaissance\":null,\"numIdendite\":null,\"photo\":null"
                                        + ",\"domicile\":null,\"estAssure\":null,\"assurance\":null,\"tauxAssurance\":null,\"email\":null,\"numAffeliation"
                                        + "\":null,\"postedAt\":null,\"lastUpdatedAt\":null,\"dossierMedical\":null,\"priseRDVs\":null,\"paiement\":null,"
                                        + "\"salleAttente\":null,\"detailsPatient\":null}"));
    }

    /**
     * Method under test: {@link PatientController#getPatientsBySalle(Long)}
     */
    @Test
    void testGetPatientsBySalle() throws Exception {
        when(patientService.getPatientBySalleId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Patient/getAllBySalle/{id}", 123L);
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PatientController#getPatientsBySalle(Long)}
     */
    @Test
    void testGetPatientsBySalle2() throws Exception {
        when(patientService.getPatientBySalleId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/Patient/getAllBySalle/{id}", 123L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

