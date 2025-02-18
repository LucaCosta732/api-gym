package it.lucacosta.gym.controller;

import it.lucacosta.gym.dto.request.SchedaAllenamentoRequest;
import it.lucacosta.gym.dto.response.AllenatoreResponse;
import it.lucacosta.gym.dto.response.EsercizioResponse;
import it.lucacosta.gym.dto.response.SchedaAllenamentoResponse;
import it.lucacosta.gym.dto.response.UtenteResponse;
import it.lucacosta.gym.service.SchedaAllenamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@SuppressWarnings("null")
public class SchedaAllenamentoControllerTest {

    @Autowired
    private SchedaAllenamentoController schedaAllenamentoController;

    @MockitoBean
    private SchedaAllenamentoService schedaAllenamentoService;

    private SchedaAllenamentoResponse schedaAllenamentoTest;
    private SchedaAllenamentoRequest schedaAllenamentoRequest;
    private List<SchedaAllenamentoResponse> schedeAllenamentoTestList;
    private UtenteResponse utenteTest;
    private AllenatoreResponse allenatoreTest;
    private EsercizioResponse esercizioTest;
    private List<EsercizioResponse> eserciziTestList;
    private List<Long> eserciziIdsList;

    @BeforeEach
    void setup() {
        utenteTest = new UtenteResponse();
        utenteTest.setId(1L);
        utenteTest.setNome("Mario");
        utenteTest.setCognome("Rossi");
        utenteTest.setEmail("mario.rossi@example.com");
        utenteTest.setTelefono("3931234567");
        utenteTest.setDataIscrizione(Date.valueOf(LocalDate.now()));

        allenatoreTest = new AllenatoreResponse();
        allenatoreTest.setId(1L);
        allenatoreTest.setNome("Giovanni");
        allenatoreTest.setCognome("Bianchi");
        allenatoreTest.setEmail("giovanni.bianchi@example.com");
        allenatoreTest.setSpecializzazione("Cardio");
        allenatoreTest.setTelefono("3441234567");

        esercizioTest = new EsercizioResponse();
        esercizioTest.setId(1L);
        esercizioTest.setNome("Squat");
        esercizioTest.setDescrizione("Esercizio per le gambe");
        esercizioTest.setGruppoMuscolare("Gambe");
        esercizioTest.setAttrezzatura("Corpo libero");

        eserciziTestList = new ArrayList<>();
        eserciziTestList.add(esercizioTest);

        eserciziIdsList = new ArrayList<>();
        eserciziIdsList.add(1L);

        schedaAllenamentoTest = new SchedaAllenamentoResponse();
        schedaAllenamentoTest.setId(1L);
        schedaAllenamentoTest.setNome("Scheda Gambe");
        schedaAllenamentoTest.setUtente(utenteTest);
        schedaAllenamentoTest.setAllenatore(allenatoreTest);
        schedaAllenamentoTest.setDataCreazione(Date.valueOf(LocalDate.now()));
        schedaAllenamentoTest.setDataFine(Date.valueOf(LocalDate.now().plusMonths(3)));
        schedaAllenamentoTest.setEsercizio(eserciziTestList);

        schedeAllenamentoTestList = new ArrayList<>();
        schedeAllenamentoTestList.add(schedaAllenamentoTest);

        schedaAllenamentoRequest = new SchedaAllenamentoRequest();
        schedaAllenamentoRequest.setNome("Nuova Scheda");
        schedaAllenamentoRequest.setDataFine(Date.valueOf(LocalDate.now().plusMonths(1)));
    }

    @Test
    void testGetSchedaAllenamentoById_inputValidi_ritornaOK() {
        when(schedaAllenamentoService.getSchedaAllenamentoById(1L)).thenReturn(schedaAllenamentoTest);

        ResponseEntity<SchedaAllenamentoResponse> risultato = schedaAllenamentoController.getSchedaAllenamentoById(1L);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(schedaAllenamentoTest.getId(), risultato.getBody().getId());
        assertEquals(schedaAllenamentoTest.getNome(), risultato.getBody().getNome());
        assertEquals(schedaAllenamentoTest.getUtente().getId(), risultato.getBody().getUtente().getId());
        assertEquals(schedaAllenamentoTest.getAllenatore().getId(), risultato.getBody().getAllenatore().getId());
        assertEquals(schedaAllenamentoTest.getDataCreazione(), risultato.getBody().getDataCreazione());
        assertEquals(schedaAllenamentoTest.getDataFine(), risultato.getBody().getDataFine());
        assertEquals(schedaAllenamentoTest.getEsercizio().size(), risultato.getBody().getEsercizio().size());
    }

    @Test
    void testGetAllSchedeAllenamento_ritornaOK() {
        when(schedaAllenamentoService.getAllSchedeAllenamento()).thenReturn(schedeAllenamentoTestList);

        ResponseEntity<List<SchedaAllenamentoResponse>> risultato = schedaAllenamentoController
                .getAllSchedeAllenamento();

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertFalse(risultato.getBody().isEmpty());
        assertEquals(1, risultato.getBody().size());
        assertEquals(schedeAllenamentoTestList.get(0).getNome(), risultato.getBody().get(0).getNome());
    }

    @Test
    void testGetAllSchedeAllenamento_listaVuota_ritornaOK() {
        when(schedaAllenamentoService.getAllSchedeAllenamento()).thenReturn(Collections.emptyList());

        ResponseEntity<List<SchedaAllenamentoResponse>> risultato = schedaAllenamentoController
                .getAllSchedeAllenamento();

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertTrue(risultato.getBody().isEmpty());
    }

    @Test
    void testCreateSchedaAllenamento_inputValidi_ritornaOK() {
        Long utenteId = 1L;
        Long allenatoreId = 1L;
        when(schedaAllenamentoService.createSchedaAllenamento(utenteId, allenatoreId, schedaAllenamentoRequest))
                .thenReturn(schedaAllenamentoTest);

        ResponseEntity<SchedaAllenamentoResponse> risultato = schedaAllenamentoController
                .createSchedaAllenamento(utenteId, allenatoreId, schedaAllenamentoRequest);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(schedaAllenamentoTest.getNome(), risultato.getBody().getNome());
        assertEquals(schedaAllenamentoTest.getUtente().getId(), risultato.getBody().getUtente().getId());
        assertEquals(schedaAllenamentoTest.getAllenatore().getId(), risultato.getBody().getAllenatore().getId());
    }

    @Test
    void testUpdateEserciziDaAggiungere_inputValidi_ritornaOK() {
        Long schedaId = 1L;
        when(schedaAllenamentoService.updateAggiungiEsercizi(schedaId, eserciziIdsList))
                .thenReturn(schedaAllenamentoTest);

        ResponseEntity<SchedaAllenamentoResponse> risultato = schedaAllenamentoController
                .updateEserciziDaAggiungere(schedaId, eserciziIdsList);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(schedaAllenamentoTest.getId(), risultato.getBody().getId());
        assertEquals(schedaAllenamentoTest.getEsercizio().size(), risultato.getBody().getEsercizio().size()); }

    @Test
    void testUpdateEserciziDaRimuovere_inputValidi_ritornaOK() {
        Long schedaId = 1L;
        when(schedaAllenamentoService.updateRimuoviEsercizi(schedaId, eserciziIdsList))
                .thenReturn(schedaAllenamentoTest);

        ResponseEntity<SchedaAllenamentoResponse> risultato = schedaAllenamentoController
                .updateEserciziDaRimuovere(schedaId, eserciziIdsList);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(schedaAllenamentoTest.getId(), risultato.getBody().getId());
    }

    @Test
    void testUpdateAllenatore_inputValidi_ritornaOK() {
        Long schedaId = 1L;
        Long nuovoAllenatoreId = 2L; 
        when(schedaAllenamentoService.updateAllenatore(schedaId, nuovoAllenatoreId)).thenReturn(schedaAllenamentoTest);

        ResponseEntity<SchedaAllenamentoResponse> risultato = schedaAllenamentoController.updateAllenatore(schedaId,
                nuovoAllenatoreId);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(schedaAllenamentoTest.getId(), risultato.getBody().getId());
        assertEquals(schedaAllenamentoTest.getAllenatore().getId(), risultato.getBody().getAllenatore().getId());
    }

    @Test
    void testUpdateSchedaAllenamento_inputValidi_ritornaOK() {
        Long schedaId = 1L;
        when(schedaAllenamentoService.updateSchedaAllenamento(schedaAllenamentoRequest, schedaId))
                .thenReturn(schedaAllenamentoTest);

        ResponseEntity<SchedaAllenamentoResponse> risultato = schedaAllenamentoController
                .updateSchedaAllenamento(schedaAllenamentoRequest, schedaId);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(schedaAllenamentoTest.getId(), risultato.getBody().getId());
        assertEquals(schedaAllenamentoTest.getNome(), risultato.getBody().getNome()); 
    }

    @Test
    void testDeleteSchedaAllenamento_inputValidi_ritornaOK() {
        Long schedaId = 1L;
        when(schedaAllenamentoService.deleteSchedaAllenamento(schedaId)).thenReturn(true);

        ResponseEntity<Boolean> risultato = schedaAllenamentoController.deleteSchedaAllenamento(schedaId);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertTrue(risultato.getBody());
    }
}