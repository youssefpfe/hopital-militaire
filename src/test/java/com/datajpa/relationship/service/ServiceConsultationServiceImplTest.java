package com.datajpa.relationship.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.qos.logback.core.rolling.helper.RollingCalendar;
import com.datajpa.relationship.dto.request.ServiceConsultationRequestDto;
import com.datajpa.relationship.dto.response.ServiceConsultationResponseDto;
import com.datajpa.relationship.model.Consultant;
import com.datajpa.relationship.model.DossierMedical;
import com.datajpa.relationship.model.Patient;
import com.datajpa.relationship.model.PersonnelMedical;
import com.datajpa.relationship.model.PriseRDV;
import com.datajpa.relationship.model.SalleAttente;
import com.datajpa.relationship.model.ServiceConsultation;
import com.datajpa.relationship.repository.OrdonnanceRepository;
import com.datajpa.relationship.repository.ServiceConsultationRepository;
import com.datajpa.relationship.repository.UtilisateurRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ServiceConsultationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ServiceConsultationServiceImplTest {
    @MockBean
    private OrdonnanceRepository ordonnanceRepository;

    @MockBean
    private SalleAttenteService salleAttenteService;

    @MockBean
    private ServiceConsultationRepository serviceConsultationRepository;

    @Autowired
    private ServiceConsultationServiceImpl serviceConsultationServiceImpl;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#getServiceConsultation(Long)}
     */
    @Test
    void testGetServiceConsultation() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");
        Optional<ServiceConsultation> ofResult = Optional.of(serviceConsultation);
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(serviceConsultation, serviceConsultationServiceImpl.getServiceConsultation(123L));
        verify(serviceConsultationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#getServiceConsultation(Long)}
     */
    @Test
    void testGetServiceConsultation2() {
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> serviceConsultationServiceImpl.getServiceConsultation(123L));
        verify(serviceConsultationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#getServiceConsultation(Long)}
     */
    @Test
    void testGetServiceConsultation3() {
        when(serviceConsultationRepository.findById((Long) any())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> serviceConsultationServiceImpl.getServiceConsultation(123L));
        verify(serviceConsultationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#addServiceConsultation(ServiceConsultationRequestDto)}
     */
    @Test
    void testAddServiceConsultation() {
        assertThrows(IllegalArgumentException.class,
                () -> serviceConsultationServiceImpl.addServiceConsultation(new ServiceConsultationRequestDto()));
    }



    /**
     * Method under test: {@link ServiceConsultationServiceImpl#addServiceConsultation(ServiceConsultationRequestDto)}
     */
    @Test
    void testAddServiceConsultation3() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        ArrayList<Patient> patientList = new ArrayList<>();
        salleAttente.setPatients(patientList);
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());
        when(salleAttenteService.getSalleAttente((Long) any())).thenReturn(salleAttente);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation.setLastUpdatedAt(fromResult);
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation.setPostedAt(fromResult1);
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente1);
        serviceConsultation.setTypeService("Type Service");
        when(serviceConsultationRepository.save((ServiceConsultation) any())).thenReturn(serviceConsultation);
        ServiceConsultationResponseDto actualAddServiceConsultationResult = serviceConsultationServiceImpl
                .addServiceConsultation(new ServiceConsultationRequestDto("service consultation at least on salle attente",
                        "service consultation at least on salle attente", 123L));
        assertEquals(patientList, actualAddServiceConsultationResult.getConsultants());
        assertEquals("Type Service", actualAddServiceConsultationResult.getTypeService());
        assertEquals(salleAttente, actualAddServiceConsultationResult.getSalleAttente());
        assertEquals(patientList, actualAddServiceConsultationResult.getPriseRDVS());
        assertEquals(patientList, actualAddServiceConsultationResult.getDossierMedicals());
        assertSame(fromResult, actualAddServiceConsultationResult.getLastUpdatedAt());
        assertEquals("Nom Service", actualAddServiceConsultationResult.getNomService());
        assertEquals(123L, actualAddServiceConsultationResult.getId().longValue());
        assertEquals(patientList, actualAddServiceConsultationResult.getPersonnelMedicals());
        assertSame(fromResult1, actualAddServiceConsultationResult.getPostedAt());
        verify(salleAttenteService).getSalleAttente((Long) any());
        verify(serviceConsultationRepository).save((ServiceConsultation) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#addServiceConsultation(ServiceConsultationRequestDto)}
     */
    @Test
    void testAddServiceConsultation4() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());
        when(salleAttenteService.getSalleAttente((Long) any())).thenReturn(salleAttente);
        when(serviceConsultationRepository.save((ServiceConsultation) any())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class,
                () -> serviceConsultationServiceImpl.addServiceConsultation(
                        new ServiceConsultationRequestDto("service consultation at least on salle attente",
                                "service consultation at least on salle attente", 123L)));
        verify(salleAttenteService).getSalleAttente((Long) any());
        verify(serviceConsultationRepository).save((ServiceConsultation) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#getServiceConsultationById(Long)}
     */
    @Test
    void testGetServiceConsultationById() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        ArrayList<Patient> patientList = new ArrayList<>();
        salleAttente.setPatients(patientList);
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        ArrayList<DossierMedical> dossierMedicalList = new ArrayList<>();
        serviceConsultation.setDossierMedicals(dossierMedicalList);
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation.setLastUpdatedAt(fromResult);
        serviceConsultation.setNomService("Nom Service");
        ArrayList<PersonnelMedical> personnelMedicalList = new ArrayList<>();
        serviceConsultation.setPersonnelMedicals(personnelMedicalList);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation.setPostedAt(fromResult1);
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");
        Optional<ServiceConsultation> ofResult = Optional.of(serviceConsultation);
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(ofResult);
        ServiceConsultationResponseDto actualServiceConsultationById = serviceConsultationServiceImpl
                .getServiceConsultationById(123L);
        assertEquals(dossierMedicalList, actualServiceConsultationById.getConsultants());
        assertEquals("Type Service", actualServiceConsultationById.getTypeService());
        assertSame(salleAttente, actualServiceConsultationById.getSalleAttente());
        assertEquals(dossierMedicalList, actualServiceConsultationById.getPriseRDVS());
        assertEquals(personnelMedicalList, actualServiceConsultationById.getDossierMedicals());
        assertSame(fromResult, actualServiceConsultationById.getLastUpdatedAt());
        assertEquals("Nom Service", actualServiceConsultationById.getNomService());
        assertEquals(123L, actualServiceConsultationById.getId().longValue());
        assertEquals(patientList, actualServiceConsultationById.getPersonnelMedicals());
        assertSame(fromResult1, actualServiceConsultationById.getPostedAt());
        verify(serviceConsultationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#getServiceConsultationById(Long)}
     */
    @Test
    void testGetServiceConsultationById2() {
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,
                () -> serviceConsultationServiceImpl.getServiceConsultationById(123L));
        verify(serviceConsultationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#getServiceConsultationById(Long)}
     */
    @Test
    void testGetServiceConsultationById3() {
        when(serviceConsultationRepository.findById((Long) any())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class,
                () -> serviceConsultationServiceImpl.getServiceConsultationById(123L));
        verify(serviceConsultationRepository).findById((Long) any());
    }





    /**
     * Method under test: {@link ServiceConsultationServiceImpl#getServiceConsultations()}
     */
    @Test
    void testGetServiceConsultations3() {
        when(serviceConsultationRepository.findAll()).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> serviceConsultationServiceImpl.getServiceConsultations());
        verify(serviceConsultationRepository).findAll();
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#deleteServiceConsultation(Long)}
     */
    @Test
    void testDeleteServiceConsultation() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        ArrayList<Consultant> consultantList = new ArrayList<>();
        serviceConsultation.setConsultants(consultantList);
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation.setLastUpdatedAt(fromResult);
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation.setPostedAt(fromResult1);
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");
        Optional<ServiceConsultation> ofResult = Optional.of(serviceConsultation);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(salleAttente1);
        serviceConsultation1.setTypeService("Type Service");
        when(serviceConsultationRepository.save((ServiceConsultation) any())).thenReturn(serviceConsultation1);
        doNothing().when(serviceConsultationRepository).delete((ServiceConsultation) any());
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(ofResult);
        ServiceConsultationResponseDto actualDeleteServiceConsultationResult = serviceConsultationServiceImpl
                .deleteServiceConsultation(123L);
        assertNull(actualDeleteServiceConsultationResult.getConsultants());
        assertEquals("Type Service", actualDeleteServiceConsultationResult.getTypeService());
        assertEquals(salleAttente1, actualDeleteServiceConsultationResult.getSalleAttente());
        assertEquals(consultantList, actualDeleteServiceConsultationResult.getPriseRDVS());
        assertEquals(consultantList, actualDeleteServiceConsultationResult.getDossierMedicals());
        assertSame(fromResult, actualDeleteServiceConsultationResult.getLastUpdatedAt());
        assertEquals("Nom Service", actualDeleteServiceConsultationResult.getNomService());
        assertEquals(123L, actualDeleteServiceConsultationResult.getId().longValue());
        assertNull(actualDeleteServiceConsultationResult.getPersonnelMedicals());
        assertSame(fromResult1, actualDeleteServiceConsultationResult.getPostedAt());
        verify(serviceConsultationRepository).save((ServiceConsultation) any());
        verify(serviceConsultationRepository).findById((Long) any());
        verify(serviceConsultationRepository).delete((ServiceConsultation) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#deleteServiceConsultation(Long)}
     */
    @Test
    void testDeleteServiceConsultation2() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");
        Optional<ServiceConsultation> ofResult = Optional.of(serviceConsultation);
        when(serviceConsultationRepository.save((ServiceConsultation) any())).thenThrow(new RuntimeException());
        doThrow(new RuntimeException()).when(serviceConsultationRepository).delete((ServiceConsultation) any());
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> serviceConsultationServiceImpl.deleteServiceConsultation(123L));
        verify(serviceConsultationRepository).save((ServiceConsultation) any());
        verify(serviceConsultationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#deleteServiceConsultation(Long)}
     */
    @Test
    void testDeleteServiceConsultation3() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        consultant.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant.setConsultations(new ArrayList<>());
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(new HashSet<>());
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");

        ArrayList<Consultant> consultantList = new ArrayList<>();
        consultantList.add(consultant);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(consultantList);
        ArrayList<DossierMedical> dossierMedicalList = new ArrayList<>();
        serviceConsultation1.setDossierMedicals(dossierMedicalList);
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation1.setLastUpdatedAt(fromResult);
        serviceConsultation1.setNomService("Nom Service");
        ArrayList<PersonnelMedical> personnelMedicalList = new ArrayList<>();
        serviceConsultation1.setPersonnelMedicals(personnelMedicalList);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation1.setPostedAt(fromResult1);
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(salleAttente1);
        serviceConsultation1.setTypeService("Type Service");
        Optional<ServiceConsultation> ofResult = Optional.of(serviceConsultation1);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        salleAttente2.setPatients(new ArrayList<>());
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation2 = new ServiceConsultation();
        serviceConsultation2.setConsultants(new ArrayList<>());
        serviceConsultation2.setDossierMedicals(new ArrayList<>());
        serviceConsultation2.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setNomService("Nom Service");
        serviceConsultation2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setPriseRDVS(new ArrayList<>());
        serviceConsultation2.setSalleAttente(salleAttente2);
        serviceConsultation2.setTypeService("Type Service");
        when(serviceConsultationRepository.save((ServiceConsultation) any())).thenReturn(serviceConsultation2);
        doNothing().when(serviceConsultationRepository).delete((ServiceConsultation) any());
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(ofResult);

        SalleAttente salleAttente3 = new SalleAttente();
        salleAttente3.setEtage("Etage");
        salleAttente3.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente3.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente3.setNom("Nom");
        salleAttente3.setNumOrdre(10);
        salleAttente3.setPatients(new ArrayList<>());
        salleAttente3.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente3.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente3.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation3 = new ServiceConsultation();
        serviceConsultation3.setConsultants(new ArrayList<>());
        serviceConsultation3.setDossierMedicals(new ArrayList<>());
        serviceConsultation3.setId(123L);
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation3.setLastUpdatedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation3.setNomService("Nom Service");
        serviceConsultation3.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation3.setPostedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation3.setPriseRDVS(new ArrayList<>());
        serviceConsultation3.setSalleAttente(salleAttente3);
        serviceConsultation3.setTypeService("Type Service");

        Consultant consultant1 = new Consultant();
        consultant1.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant1.setConsultations(new ArrayList<>());
        consultant1.setDossierMedicals(new ArrayList<>());
        consultant1.setEmail("jane.doe@example.org");
        consultant1.setFonction("Fonction");
        consultant1.setId(123L);
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setLastUpdatedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setNom("Nom");
        consultant1.setOrdonnances(new ArrayList<>());
        consultant1.setPassword("iloveyou");
        consultant1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setPostedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setPrenom("Prenom");
        consultant1.setPriseRDVs(new ArrayList<>());
        consultant1.setRoles(new HashSet<>());
        consultant1.setServiceConsultation(serviceConsultation3);
        consultant1.setTelephone("4105551212");
        consultant1.setTitre("Titre");
        consultant1.setUsername("janedoe");
        when(utilisateurRepository.save((Consultant) any())).thenReturn(consultant1);
        ServiceConsultationResponseDto actualDeleteServiceConsultationResult = serviceConsultationServiceImpl
                .deleteServiceConsultation(123L);
        assertNull(actualDeleteServiceConsultationResult.getConsultants());
        assertEquals("Type Service", actualDeleteServiceConsultationResult.getTypeService());
        assertEquals(salleAttente2, actualDeleteServiceConsultationResult.getSalleAttente());
        assertEquals(dossierMedicalList, actualDeleteServiceConsultationResult.getPriseRDVS());
        assertEquals(personnelMedicalList, actualDeleteServiceConsultationResult.getDossierMedicals());
        assertSame(fromResult, actualDeleteServiceConsultationResult.getLastUpdatedAt());
        assertEquals("Nom Service", actualDeleteServiceConsultationResult.getNomService());
        assertEquals(123L, actualDeleteServiceConsultationResult.getId().longValue());
        assertNull(actualDeleteServiceConsultationResult.getPersonnelMedicals());
        assertSame(fromResult1, actualDeleteServiceConsultationResult.getPostedAt());
        verify(serviceConsultationRepository).save((ServiceConsultation) any());
        verify(serviceConsultationRepository).findById((Long) any());
        verify(serviceConsultationRepository).delete((ServiceConsultation) any());
        verify(utilisateurRepository).save((Consultant) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#editServiceConsultation(Long, ServiceConsultationRequestDto)}
     */
    @Test
    void testEditServiceConsultation() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");
        Optional<ServiceConsultation> ofResult = Optional.of(serviceConsultation);
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class,
                () -> serviceConsultationServiceImpl.editServiceConsultation(123L, new ServiceConsultationRequestDto()));
        verify(serviceConsultationRepository).findById((Long) any());
    }



    /**
     * Method under test: {@link ServiceConsultationServiceImpl#editServiceConsultation(Long, ServiceConsultationRequestDto)}
     */
    @Test
    void testEditServiceConsultation3() {
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,
                () -> serviceConsultationServiceImpl.editServiceConsultation(123L, new ServiceConsultationRequestDto()));
        verify(serviceConsultationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#editServiceConsultation(Long, ServiceConsultationRequestDto)}
     */
    @Test
    void testEditServiceConsultation4() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());
        when(salleAttenteService.getSalleAttente((Long) any())).thenReturn(salleAttente);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        ArrayList<Consultant> consultantList = new ArrayList<>();
        serviceConsultation.setConsultants(consultantList);
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente1);
        serviceConsultation.setTypeService("Type Service");
        Optional<ServiceConsultation> ofResult = Optional.of(serviceConsultation);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        salleAttente2.setPatients(new ArrayList<>());
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation1.setLastUpdatedAt(fromResult);
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation1.setPostedAt(fromResult1);
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(salleAttente2);
        serviceConsultation1.setTypeService("Type Service");
        when(serviceConsultationRepository.save((ServiceConsultation) any())).thenReturn(serviceConsultation1);
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(ofResult);
        ServiceConsultationResponseDto actualEditServiceConsultationResult = serviceConsultationServiceImpl
                .editServiceConsultation(123L,
                        new ServiceConsultationRequestDto("service consultation at least on salle attente",
                                "service consultation at least on salle attente", 123L));
        assertEquals(consultantList, actualEditServiceConsultationResult.getConsultants());
        assertEquals("Type Service", actualEditServiceConsultationResult.getTypeService());
        assertEquals(salleAttente1, actualEditServiceConsultationResult.getSalleAttente());
        assertEquals(consultantList, actualEditServiceConsultationResult.getPriseRDVS());
        assertEquals(consultantList, actualEditServiceConsultationResult.getDossierMedicals());
        assertSame(fromResult, actualEditServiceConsultationResult.getLastUpdatedAt());
        assertEquals("Nom Service", actualEditServiceConsultationResult.getNomService());
        assertEquals(123L, actualEditServiceConsultationResult.getId().longValue());
        assertEquals(consultantList, actualEditServiceConsultationResult.getPersonnelMedicals());
        assertSame(fromResult1, actualEditServiceConsultationResult.getPostedAt());
        verify(salleAttenteService).getSalleAttente((Long) any());
        verify(serviceConsultationRepository).save((ServiceConsultation) any());
        verify(serviceConsultationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#editServiceConsultation(Long, ServiceConsultationRequestDto)}
     */
    @Test
    void testEditServiceConsultation5() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());
        when(salleAttenteService.getSalleAttente((Long) any())).thenReturn(salleAttente);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente1);
        serviceConsultation.setTypeService("Type Service");
        Optional<ServiceConsultation> ofResult = Optional.of(serviceConsultation);
        when(serviceConsultationRepository.save((ServiceConsultation) any())).thenThrow(new IllegalArgumentException());
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class,
                () -> serviceConsultationServiceImpl.editServiceConsultation(123L,
                        new ServiceConsultationRequestDto("service consultation at least on salle attente",
                                "service consultation at least on salle attente", 123L)));
        verify(salleAttenteService).getSalleAttente((Long) any());
        verify(serviceConsultationRepository).save((ServiceConsultation) any());
        verify(serviceConsultationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#addSalleAttenteToServiceConsultation(Long, Long)}
     */
    @Test
    void testAddSalleAttenteToServiceConsultation() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());
        when(salleAttenteService.getSalleAttente((Long) any())).thenReturn(salleAttente);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente1);
        serviceConsultation.setTypeService("Type Service");
        Optional<ServiceConsultation> ofResult = Optional.of(serviceConsultation);
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class,
                () -> serviceConsultationServiceImpl.addSalleAttenteToServiceConsultation(123L, 123L));
        verify(salleAttenteService).getSalleAttente((Long) any());
        verify(serviceConsultationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#addSalleAttenteToServiceConsultation(Long, Long)}
     */
    @Test
    void testAddSalleAttenteToServiceConsultation2() {
        when(salleAttenteService.getSalleAttente((Long) any())).thenThrow(new IllegalArgumentException());

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");
        Optional<ServiceConsultation> ofResult = Optional.of(serviceConsultation);
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class,
                () -> serviceConsultationServiceImpl.addSalleAttenteToServiceConsultation(123L, 123L));
        verify(salleAttenteService).getSalleAttente((Long) any());
        verify(serviceConsultationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#removeSalleAttenteFromServiceConsultation(Long)}
     */
    @Test
    void testRemoveSalleAttenteFromServiceConsultation() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());
        when(salleAttenteService.getSalleAttente((Long) any())).thenReturn(salleAttente);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        ArrayList<Patient> patientList = new ArrayList<>();
        salleAttente1.setPatients(patientList);
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        ArrayList<DossierMedical> dossierMedicalList = new ArrayList<>();
        serviceConsultation.setDossierMedicals(dossierMedicalList);
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation.setLastUpdatedAt(fromResult);
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation.setPostedAt(fromResult1);
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente1);
        serviceConsultation.setTypeService("Type Service");
        Optional<ServiceConsultation> ofResult = Optional.of(serviceConsultation);
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(ofResult);
        ServiceConsultationResponseDto actualRemoveSalleAttenteFromServiceConsultationResult = serviceConsultationServiceImpl
                .removeSalleAttenteFromServiceConsultation(123L);
        assertEquals(dossierMedicalList, actualRemoveSalleAttenteFromServiceConsultationResult.getConsultants());
        assertEquals("Type Service", actualRemoveSalleAttenteFromServiceConsultationResult.getTypeService());
        assertNull(actualRemoveSalleAttenteFromServiceConsultationResult.getSalleAttente());
        assertEquals(dossierMedicalList, actualRemoveSalleAttenteFromServiceConsultationResult.getPriseRDVS());
        assertSame(fromResult1, actualRemoveSalleAttenteFromServiceConsultationResult.getPostedAt());
        assertEquals(dossierMedicalList, actualRemoveSalleAttenteFromServiceConsultationResult.getPersonnelMedicals());
        assertEquals("Nom Service", actualRemoveSalleAttenteFromServiceConsultationResult.getNomService());
        assertSame(fromResult, actualRemoveSalleAttenteFromServiceConsultationResult.getLastUpdatedAt());
        assertEquals(123L, actualRemoveSalleAttenteFromServiceConsultationResult.getId().longValue());
        assertEquals(patientList, actualRemoveSalleAttenteFromServiceConsultationResult.getDossierMedicals());
        verify(salleAttenteService).getSalleAttente((Long) any());
        verify(serviceConsultationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#removeSalleAttenteFromServiceConsultation(Long)}
     */
    @Test
    void testRemoveSalleAttenteFromServiceConsultation2() {
        when(salleAttenteService.getSalleAttente((Long) any())).thenThrow(new IllegalArgumentException());

        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");
        Optional<ServiceConsultation> ofResult = Optional.of(serviceConsultation);
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class,
                () -> serviceConsultationServiceImpl.removeSalleAttenteFromServiceConsultation(123L));
        verify(salleAttenteService).getSalleAttente((Long) any());
        verify(serviceConsultationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ServiceConsultationServiceImpl#removeSalleAttenteFromServiceConsultation(Long)}
     */
    @Test
    void testRemoveSalleAttenteFromServiceConsultation3() {
        SalleAttente salleAttente = mock(SalleAttente.class);
        doNothing().when(salleAttente).removeServiceConsultation((ServiceConsultation) any());
        doNothing().when(salleAttente).setEtage((String) any());
        doNothing().when(salleAttente).setId((Long) any());
        doNothing().when(salleAttente).setLastUpdatedAt((Date) any());
        doNothing().when(salleAttente).setNom((String) any());
        doNothing().when(salleAttente).setNumOrdre((Integer) any());
        doNothing().when(salleAttente).setPatients((List<Patient>) any());
        doNothing().when(salleAttente).setPersonnelMedicals((List<PersonnelMedical>) any());
        doNothing().when(salleAttente).setPostedAt((Date) any());
        doNothing().when(salleAttente).setServiceConsultations((List<ServiceConsultation>) any());
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());
        when(salleAttenteService.getSalleAttente((Long) any())).thenReturn(salleAttente);

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        ArrayList<Patient> patientList = new ArrayList<>();
        salleAttente1.setPatients(patientList);
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        ArrayList<DossierMedical> dossierMedicalList = new ArrayList<>();
        serviceConsultation.setDossierMedicals(dossierMedicalList);
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation.setLastUpdatedAt(fromResult);
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        serviceConsultation.setPostedAt(fromResult1);
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente1);
        serviceConsultation.setTypeService("Type Service");
        Optional<ServiceConsultation> ofResult = Optional.of(serviceConsultation);
        when(serviceConsultationRepository.findById((Long) any())).thenReturn(ofResult);
        ServiceConsultationResponseDto actualRemoveSalleAttenteFromServiceConsultationResult = serviceConsultationServiceImpl
                .removeSalleAttenteFromServiceConsultation(123L);
        assertEquals(dossierMedicalList, actualRemoveSalleAttenteFromServiceConsultationResult.getConsultants());
        assertEquals("Type Service", actualRemoveSalleAttenteFromServiceConsultationResult.getTypeService());
        assertNull(actualRemoveSalleAttenteFromServiceConsultationResult.getSalleAttente());
        assertEquals(dossierMedicalList, actualRemoveSalleAttenteFromServiceConsultationResult.getPriseRDVS());
        assertSame(fromResult1, actualRemoveSalleAttenteFromServiceConsultationResult.getPostedAt());
        assertEquals(dossierMedicalList, actualRemoveSalleAttenteFromServiceConsultationResult.getPersonnelMedicals());
        assertEquals("Nom Service", actualRemoveSalleAttenteFromServiceConsultationResult.getNomService());
        assertSame(fromResult, actualRemoveSalleAttenteFromServiceConsultationResult.getLastUpdatedAt());
        assertEquals(123L, actualRemoveSalleAttenteFromServiceConsultationResult.getId().longValue());
        assertEquals(patientList, actualRemoveSalleAttenteFromServiceConsultationResult.getDossierMedicals());
        verify(salleAttenteService).getSalleAttente((Long) any());
        verify(salleAttente).removeServiceConsultation((ServiceConsultation) any());
        verify(salleAttente).setEtage((String) any());
        verify(salleAttente).setId((Long) any());
        verify(salleAttente).setLastUpdatedAt((Date) any());
        verify(salleAttente).setNom((String) any());
        verify(salleAttente).setNumOrdre((Integer) any());
        verify(salleAttente).setPatients((List<Patient>) any());
        verify(salleAttente).setPersonnelMedicals((List<PersonnelMedical>) any());
        verify(salleAttente).setPostedAt((Date) any());
        verify(salleAttente).setServiceConsultations((List<ServiceConsultation>) any());
        verify(serviceConsultationRepository).findById((Long) any());
    }


}

