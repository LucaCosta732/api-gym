package it.lucacosta.gym.controller;


import it.lucacosta.gym.dto.request.EsercizioRequest;
import it.lucacosta.gym.dto.response.EsercizioResponse;
import it.lucacosta.gym.service.EsercizioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SuppressWarnings("null")
@SpringBootTest
public class EsercizioControllerTest {

    @Autowired
    private EsercizioController esercizioController;

    @MockitoBean
    private EsercizioService esercizioService;

    private EsercizioResponse esercizioTest;
    private EsercizioRequest esercizioRequest;
    private List<EsercizioResponse> eserciziTestList;
    private List<EsercizioRequest> eserciziRequestList;

    @BeforeEach
    void setup() {
        esercizioTest = new EsercizioResponse();
        esercizioTest.setId(1L);
        esercizioTest.setNome("Squat");
        esercizioTest.setDescrizione("Esercizio base per le gambe");
        esercizioTest.setGruppoMuscolare("Gambe");
        esercizioTest.setAttrezzatura("Bilanciere");

        eserciziTestList = new ArrayList<>();
        eserciziTestList.add(esercizioTest);

        esercizioRequest = new EsercizioRequest();
        esercizioRequest.setNome("Panca Piana");
        esercizioRequest.setDescrizione("Esercizio per il petto");
        esercizioRequest.setGruppoMuscolare("Petto");
        esercizioRequest.setAttrezzatura("Bilanciere, Panca");

        eserciziRequestList = new ArrayList<>();
        eserciziRequestList.add(esercizioRequest);
    }

    @Test
    void testGetEsercizioById_inputValidi_ritornaOK() {
        when(esercizioService.getEsercizioById(1L)).thenReturn(esercizioTest);

        ResponseEntity<EsercizioResponse> risultato = esercizioController.getEsercizioById(1L);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(esercizioTest.getId(), risultato.getBody().getId());
        assertEquals(esercizioTest.getNome(), risultato.getBody().getNome());
        assertEquals(esercizioTest.getDescrizione(), risultato.getBody().getDescrizione());
        assertEquals(esercizioTest.getGruppoMuscolare(), risultato.getBody().getGruppoMuscolare());
        assertEquals(esercizioTest.getAttrezzatura(), risultato.getBody().getAttrezzatura());
    }

    @Test
    void testGetEsercizi_ritornaOK() {
        when(esercizioService.getEsercizi()).thenReturn(eserciziTestList);

        ResponseEntity<List<EsercizioResponse>> risultato = esercizioController.getEsercizi();

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertFalse(risultato.getBody().isEmpty());
        assertEquals(1, risultato.getBody().size());
        assertEquals(eserciziTestList.get(0).getNome(), risultato.getBody().get(0).getNome());
    }

    @Test
    void testGetEsercizi_listaVuota_ritornaOK() {
        when(esercizioService.getEsercizi()).thenReturn(Collections.emptyList());

        ResponseEntity<List<EsercizioResponse>> risultato = esercizioController.getEsercizi();

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertTrue(risultato.getBody().isEmpty());
    }

    @Test
    void testAddEsercizio_inputValidi_ritornaOK() {
        when(esercizioService.addEsercizio(esercizioRequest)).thenReturn(esercizioTest);

        ResponseEntity<EsercizioResponse> risultato = esercizioController.addEsercizio(esercizioRequest);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(esercizioTest.getNome(), risultato.getBody().getNome());
        assertEquals(esercizioTest.getDescrizione(), risultato.getBody().getDescrizione());
        assertEquals(esercizioTest.getGruppoMuscolare(), risultato.getBody().getGruppoMuscolare());
        assertEquals(esercizioTest.getAttrezzatura(), risultato.getBody().getAttrezzatura());
    }

    @Test
    void testAddEsercizi_inputValidi_ritornaOK() {
        when(esercizioService.addEsercizi(eserciziRequestList)).thenReturn(eserciziTestList);

        ResponseEntity<List<EsercizioResponse>> risultato = esercizioController.addEsercizi(eserciziRequestList);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertFalse(risultato.getBody().isEmpty());
        assertEquals(1, risultato.getBody().size());
        assertEquals(eserciziTestList.get(0).getNome(), risultato.getBody().get(0).getNome());
    }

    @Test
    void testUpdateEsercizio_inputValidi_ritornaOK() {
        Long idDaAggiornare = 1L;
        when(esercizioService.updateEsercizio(idDaAggiornare, esercizioRequest)).thenReturn(esercizioTest);

        ResponseEntity<EsercizioResponse> risultato = esercizioController.updateEsercizio(idDaAggiornare, esercizioRequest);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(esercizioTest.getId(), risultato.getBody().getId());
        assertEquals(esercizioTest.getNome(), risultato.getBody().getNome());
        assertEquals(esercizioTest.getDescrizione(), risultato.getBody().getDescrizione());
        assertEquals(esercizioTest.getGruppoMuscolare(), risultato.getBody().getGruppoMuscolare());
        assertEquals(esercizioTest.getAttrezzatura(), risultato.getBody().getAttrezzatura());
    }

    @Test
    void testDeleteEsercizio_inputValidi_ritornaOK() {
        Long idDaCancellare = 1L;
        when(esercizioService.deleteEsercizio(idDaCancellare)).thenReturn(true);

        ResponseEntity<Boolean> risultato = esercizioController.deleteEsercizio(idDaCancellare);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertTrue(risultato.getBody());
    }
}