package it.lucacosta.gym.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import it.lucacosta.gym.dto.response.AbbonamentoResponse;
import it.lucacosta.gym.dto.response.TipoAbbonamentoResponse;
import it.lucacosta.gym.dto.response.UtenteResponse;
import it.lucacosta.gym.model.Stato;
import it.lucacosta.gym.model.Tipo;
import it.lucacosta.gym.service.AbbonamentoService;

@SpringBootTest
@SuppressWarnings("null")
class AbbonamentoControllerTest {

    @Autowired
    private AbbonamentoController abbonamentoController;

    @MockitoBean
    private AbbonamentoService abbonamentoService;

    private AbbonamentoResponse abbonamentoTest;
    private List<AbbonamentoResponse> abbonamentoTestList;

    @BeforeEach
    void setup() {
        abbonamentoTest = new AbbonamentoResponse();
        abbonamentoTest.setId(1L);
        abbonamentoTest.setTipo(new TipoAbbonamentoResponse(1L, Tipo.MENSILE, "Mensile", 128D));
        abbonamentoTest.setUtente(
                new UtenteResponse(1L, "Mario", "Rossi",
                        "test@test.com", "password", Date.valueOf(LocalDate.now()),
                        "393333333333"));
        abbonamentoTest.setDataInizio(Date.valueOf(LocalDate.of(2025, 2, 18))); // 18 Febbraio 2025
        abbonamentoTest.setDataFine(Date.valueOf(LocalDate.of(2026, 2, 18))); // 18 Febbraio 2026
        abbonamentoTest.setStato(Stato.ATTIVO);

        abbonamentoTestList = new ArrayList<>();
        abbonamentoTestList.add(abbonamentoTest);
    }

    @Test
    void testGetAbbonamentoById_inputValidi_ritornaOk() {
        when(abbonamentoService.getAbbonamentoById(1L)).thenReturn(abbonamentoTest);

        ResponseEntity<AbbonamentoResponse> risultato = abbonamentoController.getAbbonamentoById(1L);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertEquals(abbonamentoTest.getUtente(), risultato.getBody().getUtente());
    }

    @Test
    void testGetAbbonamenti_inputValidi_ritornaOk() {
        when(abbonamentoService.getAbbonamenti(null)).thenReturn(abbonamentoTestList);

        ResponseEntity<List<AbbonamentoResponse>> risultato = abbonamentoController.getAbbonamenti(null);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertEquals(1, risultato.getBody().size());
    }

    @Test
    void testAddAbbonamento_inputValidi_ritornaOk() {
        when(abbonamentoService.addAbbonamento(1L, 1L)).thenReturn(abbonamentoTest);

        ResponseEntity<AbbonamentoResponse> risultato = abbonamentoController.addAbbonamento(1L, 1L);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertEquals(abbonamentoTest.getId(), risultato.getBody().getId());
    }

    @Test
    void testControlloValiditaAbbonamento_inputValidi_ritornaOk() {
        when(abbonamentoService.controlloValidita(1L)).thenReturn(Stato.ATTIVO);

        ResponseEntity<Stato> risultato = abbonamentoController.controlloValiditaAbbonamento(1L);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertEquals(Stato.ATTIVO, risultato.getBody());
    }

    @Test
    void testDeleteAbbonamento_inputValidi_ritornaOk() {
        when(abbonamentoService.deleteAbbonamento(1L)).thenReturn(true);

        ResponseEntity<Boolean> risultato = abbonamentoController.deleteAbbonamento(1L);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertTrue(risultato.getBody());
    }

    @Test
    void testControlloAbbonamentiScaduti_inputValidi_ritornaOk() {
        when(abbonamentoService.controlloAbbonamentiScaduti()).thenReturn(abbonamentoTestList);

        ResponseEntity<List<AbbonamentoResponse>> risultato = abbonamentoController.controlloAbbonamentiScaduti();

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertEquals(1, risultato.getBody().size());
    }

    @Test
    void testAttivaAbbonamento_inputValidi_ritornaOk() {
        when(abbonamentoService.attivaAbbonamento(1L, 2L)).thenReturn(abbonamentoTest);

        ResponseEntity<AbbonamentoResponse> risultato = abbonamentoController.attivaAbbonamento(1L, 2L);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertEquals(abbonamentoTest.getId(), risultato.getBody().getId());
    }
}
