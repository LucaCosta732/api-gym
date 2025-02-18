package it.lucacosta.gym.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

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

import it.lucacosta.gym.dto.request.AllenatoreRequest;
import it.lucacosta.gym.dto.response.AllenatoreResponse;
import it.lucacosta.gym.service.AllenatoreService;

@SpringBootTest
@SuppressWarnings("null")
public class AllenatoreControllerTest {

    @Autowired
    private AllenatoreController allenatoreController;

    @MockitoBean
    private AllenatoreService allenatoreService;

    private AllenatoreResponse allenatoreTest;
    private List<AllenatoreResponse> allenatoriTest;

    private AllenatoreRequest allenatoreRequest;
    private List<AllenatoreRequest> allenatoriRequestList;

    @BeforeEach
    void setup() {
        allenatoreTest = new AllenatoreResponse();
        allenatoreTest.setId(1L); // Esempio di ID
        allenatoreTest.setNome("Mario");
        allenatoreTest.setCognome("Rossi");
        allenatoreTest.setSpecializzazione("Preparazione Atletica");
        allenatoreTest.setEmail("mario.rossi@example.com");
        allenatoreTest.setTelefono("3331234567");

        allenatoriTest = new ArrayList<>();
        allenatoriTest.add(allenatoreTest);

        allenatoreRequest = new AllenatoreRequest();
        allenatoreRequest.setNome("Carlo");
        allenatoreRequest.setCognome("Verdi");
        allenatoreRequest.setSpecializzazione("Forza e Condizionamento");
        allenatoreRequest.setEmail("carlo.verdi@example.com");
        allenatoreRequest.setTelefono("3449876543");

        allenatoriRequestList = new ArrayList<>();
        allenatoriRequestList.add(allenatoreRequest);

    }

    @Test
    void testGetAllenatoreById_inputValidi_ritornaOK() {
        when(allenatoreService.getAllenatoreById(1L)).thenReturn(allenatoreTest);

        ResponseEntity<AllenatoreResponse> risultato = allenatoreController.getAllenatoreById(1L);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(allenatoreTest.getId(), risultato.getBody().getId());
        assertEquals(allenatoreTest.getNome(), risultato.getBody().getNome());
        assertEquals(allenatoreTest.getCognome(), risultato.getBody().getCognome());
        assertEquals(allenatoreTest.getEmail(), risultato.getBody().getEmail());
        assertEquals(allenatoreTest.getSpecializzazione(), risultato.getBody().getSpecializzazione());
        assertEquals(allenatoreTest.getTelefono(), risultato.getBody().getTelefono());
    }

    @Test
    void testGetAllenatori_nomePresente_ritornaOK() {
        String nome = "Mario";
        when(allenatoreService.getAllenatori(nome)).thenReturn(allenatoriTest);

        ResponseEntity<List<AllenatoreResponse>> risultato = allenatoreController.getAllenatori(nome);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertFalse(risultato.getBody().isEmpty());
        assertEquals(1, risultato.getBody().size());
        assertEquals(allenatoriTest.get(0).getNome(), risultato.getBody().get(0).getNome());
    }

    @Test
    void testGetAllenatori_nomeNonPresente_ritornaOK_ListaVuota() {
        String nomeNonPresente = "NomeNonEsistente";
        when(allenatoreService.getAllenatori(nomeNonPresente)).thenReturn(Collections.emptyList());

        ResponseEntity<List<AllenatoreResponse>> risultato = allenatoreController.getAllenatori(nomeNonPresente);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertTrue(risultato.getBody().isEmpty());
    }

    @Test
    void testAddAllenatori_inputValidi_ritornaOK() {
        when(allenatoreService.addAllenatori(allenatoriRequestList)).thenReturn(allenatoriTest);

        ResponseEntity<List<AllenatoreResponse>> risultato = allenatoreController.addAllenatori(allenatoriRequestList);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertFalse(risultato.getBody().isEmpty());
        assertEquals(1, risultato.getBody().size());
        assertEquals(allenatoriTest.get(0).getNome(), risultato.getBody().get(0).getNome());
    }

    @Test
    void testUpdateAllenatore_inputValidi_ritornaOK() {
        Long idDaAggiornare = 1L;
        when(allenatoreService.updateAllenatore(idDaAggiornare, allenatoreRequest)).thenReturn(allenatoreTest);

        ResponseEntity<AllenatoreResponse> risultato = allenatoreController.updateAllenatore(idDaAggiornare,
                allenatoreRequest);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(allenatoreTest.getId(), risultato.getBody().getId());
        assertEquals("Mario", risultato.getBody().getNome());
    }

    @Test
    void testDeleteAllenatore_inputValidi_ritornaOK() {
        Long idDaCancellare = 1L;
        when(allenatoreService.deleteAllenatore(idDaCancellare)).thenReturn(true); 

        ResponseEntity<Boolean> risultato = allenatoreController.deleteAllenatore(idDaCancellare);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody()); 
        assertTrue(risultato.getBody()); 
    }

}
