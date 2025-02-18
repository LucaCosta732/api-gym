
package it.lucacosta.gym.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import it.lucacosta.gym.dto.request.UtenteRequest;
import it.lucacosta.gym.dto.response.UtenteResponse;
import it.lucacosta.gym.service.UtenteService;

@SpringBootTest
@SuppressWarnings("null")
public class UtenteControllerTest {

    @Autowired
    private UtenteController utenteController;

    @MockitoBean
    private UtenteService utenteService;

    private UtenteResponse utenteTest;
    private UtenteRequest utenteRequest;
    private List<UtenteResponse> utentiTest;
    private List<UtenteRequest> utentiRequestList;

    @BeforeEach
    void setup() {
        utenteTest = new UtenteResponse();
        utenteTest.setId(1L);
        utenteTest.setNome("Mario");
        utenteTest.setCognome("Rossi");
        utenteTest.setEmail("mario.rossi@example.com");
        utenteTest.setDataIscrizione(Date.valueOf(LocalDate.now()));
        utenteTest.setTelefono("3931234567");

        utentiTest = new ArrayList<>();
        utentiTest.add(utenteTest);

        utenteRequest = new UtenteRequest();
        utenteRequest.setNome("Luigi");
        utenteRequest.setCognome("Bianchi");
        utenteRequest.setEmail("luigi.bianchi@example.com");
        utenteRequest.setPassword("password2");
        utenteRequest.setDataIscrizione(Date.valueOf(LocalDate.of(2024, 1, 1))); 
        utenteRequest.setTelefono("3447654321");

        utentiRequestList = new ArrayList<>();
        utentiRequestList.add(utenteRequest);
    }

    @Test
    void testGetUtenteById_inputValidi_ritornaOK() {
        when(utenteService.getUtenteById(1L)).thenReturn(utenteTest);

        ResponseEntity<UtenteResponse> risultato = utenteController.getUtenteById(1L);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(utenteTest.getId(), risultato.getBody().getId());
        assertEquals(utenteTest.getNome(), risultato.getBody().getNome());
        assertEquals(utenteTest.getCognome(), risultato.getBody().getCognome());
        assertEquals(utenteTest.getEmail(), risultato.getBody().getEmail());
        assertEquals(utenteTest.getTelefono(), risultato.getBody().getTelefono());
        assertEquals(utenteTest.getDataIscrizione(), risultato.getBody().getDataIscrizione());
    }

    @Test
    void testGetUtenti_nomePresente_ritornaOK() {
        String nome = "Mario";
        when(utenteService.getUtenti(nome)).thenReturn(utentiTest);

        ResponseEntity<List<UtenteResponse>> risultato = utenteController.getUtenti(nome);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertFalse(risultato.getBody().isEmpty());
        assertEquals(1, risultato.getBody().size());
        assertEquals(utentiTest.get(0).getNome(), risultato.getBody().get(0).getNome());
    }

    @Test
    void testGetUtenti_nomeNonPresente_ritornaOK_ListaVuota() {
        String nomeNonPresente = "NomeNonEsistente";
        when(utenteService.getUtenti(nomeNonPresente)).thenReturn(Collections.emptyList());

        ResponseEntity<List<UtenteResponse>> risultato = utenteController.getUtenti(nomeNonPresente);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertTrue(risultato.getBody().isEmpty());
    }

    @Test
    void testAddUtenti_inputValidi_ritornaOK() {
        when(utenteService.addUtenti(utentiRequestList)).thenReturn(utentiTest);

        ResponseEntity<List<UtenteResponse>> risultato = utenteController.addUtenti(utentiRequestList);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertFalse(risultato.getBody().isEmpty());
        assertEquals(1, risultato.getBody().size());
        assertEquals(utentiTest.get(0).getNome(), risultato.getBody().get(0).getNome());
    }

    @Test
    void testAddUtente_inputValidi_ritornaOK() {
        when(utenteService.addUtente(utenteRequest)).thenReturn(utenteTest);

        ResponseEntity<UtenteResponse> risultato = utenteController.addUtente(utenteRequest);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(utenteTest.getNome(), risultato.getBody().getNome());
        assertEquals(utenteTest.getCognome(), risultato.getBody().getCognome());
    }

    @Test
    void testUpdateUtente_inputValidi_ritornaOK() {
        Long idDaAggiornare = 1L;
        when(utenteService.updateUtente(utenteRequest, idDaAggiornare)).thenReturn(utenteTest);

        ResponseEntity<UtenteResponse> risultato = utenteController.updateUtente(utenteRequest, idDaAggiornare);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(utenteTest.getId(), risultato.getBody().getId());
        assertEquals(utenteTest.getNome(), risultato.getBody().getNome()); 
    }

    @Test
    void testDeleteUtente_inputValidi_ritornaOK() {
        Long idDaCancellare = 1L;
        when(utenteService.deleteUtente(idDaCancellare)).thenReturn(true);

        ResponseEntity<Boolean> risultato = utenteController.deleteUtente(idDaCancellare);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertTrue(risultato.getBody());
    }
}