package it.lucacosta.gym.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.request.AllenatoreRequest;
import it.lucacosta.gym.dto.response.AllenatoreResponse;
import it.lucacosta.gym.model.Allenatore;
import it.lucacosta.gym.repository.AllenatoreRepository;

@SpringBootTest
public class AllenatoreServiceTest {

    @Autowired
    private AllenatoreService allenatoreService;

    @MockitoBean
    private AllenatoreRepository allenatoreRepository;

    private AllenatoreResponse allenatoreResponse;
    private AllenatoreRequest allenatoreRequest;
    private List<AllenatoreResponse> allenatoreResponses;
    private List<AllenatoreRequest> allenatoreRequests;
    private Allenatore allenatore;

    @BeforeEach
    void setup() {
        Long id = 1L;
        String nome = "Luca";
        String cognome = "Costa";
        String specializzazione = "Fitness e Bodybuilding";
        String email = "luca.costa@gym.com";
        String telefono = "3912345678";

        allenatoreResponse = new AllenatoreResponse();
        allenatoreResponse.setId(id);
        allenatoreResponse.setNome(nome);
        allenatoreResponse.setCognome(cognome);
        allenatoreResponse.setSpecializzazione(specializzazione);
        allenatoreResponse.setEmail(email);
        allenatoreResponse.setTelefono(telefono);

        allenatoreResponses = new ArrayList<>();
        allenatoreResponses.add(allenatoreResponse);

        allenatoreRequest = new AllenatoreRequest();
        allenatoreRequest.setNome(nome);
        allenatoreRequest.setCognome(cognome);
        allenatoreRequest.setSpecializzazione(specializzazione);
        allenatoreRequest.setEmail(email);
        allenatoreRequest.setTelefono(telefono);

        allenatoreRequests = new ArrayList<>();
        allenatoreRequests.add(allenatoreRequest);

        allenatore = new Allenatore();
        allenatore.setId(id);
        allenatore.setNome(nome);
        allenatore.setCognome(cognome);
        allenatore.setSpecializzazione(specializzazione);
        allenatore.setEmail(email);
        allenatore.setTelefono(telefono);
    }

    @Test
    void testAddAllenatori_inputValidi_ritornaListaAllenatoreResponse() {

        List<Allenatore> allenatori = new ArrayList<>();
        allenatori.add(allenatore);
        when(allenatoreRepository.saveAll(anyList())).thenReturn(allenatori);

        List<AllenatoreResponse> risultato = allenatoreService.addAllenatori(allenatoreRequests);

        assertNotNull(risultato);
        assertEquals(allenatoreResponse.getNome(), risultato.get(0).getNome());
    }

    @Test
    void testUpdateAllenatore_inputValidi_ritornaAllenatoreResponse() {
        Long id = 1L;
        when(allenatoreRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.of(allenatore));

        AllenatoreResponse risultato = allenatoreService.updateAllenatore(id, allenatoreRequest);

        assertNotNull(risultato);
        assertEquals(allenatoreRequest.getNome(), risultato.getNome());
        assertEquals(allenatoreRequest.getCognome(), risultato.getCognome());
        assertEquals(allenatoreRequest.getSpecializzazione(), risultato.getSpecializzazione());
        assertEquals(allenatoreRequest.getEmail(), risultato.getEmail());
        assertEquals(allenatoreRequest.getTelefono(), risultato.getTelefono());
    }

    @Test
    void testUpdateAllenatore_inputInvalidi_ritornaResponseStatusException() {
        Long id = 999L;
        when(allenatoreRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.empty());

        ResponseStatusException eccezione = assertThrows(ResponseStatusException.class,
                () -> allenatoreService.updateAllenatore(id, allenatoreRequest));

        assertEquals(HttpStatus.NOT_FOUND, eccezione.getStatusCode());
        assertEquals("Allenatore non trovato", eccezione.getReason());
    }

    @Test
    void testDeleteAllenatore_inputValidi_ritornaTrue() {
        Long id = 1L;

        when(allenatoreRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.of(allenatore));
        Boolean risultato = allenatoreService.deleteAllenatore(id);

        assertTrue(risultato);
        assertTrue(allenatore.getEliminato());
    }

    @Test
    void testDeleteAllenatore_inputInvalidi_ritornaResponseStatusException() {
        Long id = 999L;

        when(allenatoreRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.empty());

        ResponseStatusException eccezione = assertThrows(ResponseStatusException.class,
                () -> allenatoreService.deleteAllenatore(id));

        assertEquals(HttpStatus.NOT_FOUND, eccezione.getStatusCode());
        assertEquals("Allenatore non trovato", eccezione.getReason());
    }

    @Test
    void testGetAllenatoreById_inputValidi_ritornaAllenatoreResponse() {
        Long id = 1L;
        when(allenatoreRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.of(allenatore));
        AllenatoreResponse risultato = allenatoreService.getAllenatoreById(id);

        assertNotNull(risultato);
        assertEquals(allenatore.getNome(), risultato.getNome());
        assertEquals(allenatore.getCognome(), risultato.getCognome());
        assertEquals(allenatore.getEmail(), risultato.getEmail());
    }

    @Test
    void testGetAllenatoreById_inputInvalidi_ritornaResponseStatusException() {
        Long id = 999L;

        when(allenatoreRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.empty());

        ResponseStatusException eccezione = assertThrows(ResponseStatusException.class,
                () -> allenatoreService.getAllenatoreById(id));

        assertEquals(HttpStatus.NOT_FOUND, eccezione.getStatusCode());
        assertEquals("Allenatore non trovato", eccezione.getReason());
    }

    @Test
    void testGetAllenatori_senzaFiltro_ritornaListaAllenatoreResponse() {
        List<Allenatore> allenatori = new ArrayList<>();
        allenatori.add(allenatore);
        when(allenatoreRepository.findAllByEliminatoFalse()).thenReturn(allenatori);

        List<AllenatoreResponse> risultato = allenatoreService.getAllenatori(null);

        assertNotNull(risultato);
        assertFalse(risultato.isEmpty());
        assertEquals(1, risultato.size());
        assertEquals(allenatori.get(0).getNome(), risultato.get(0).getNome());
    }

    @Test
    void testGetAllenatori_blank_ritornaListaAllenatoreResponse() {
        List<Allenatore> allenatori = new ArrayList<>();
        allenatori.add(allenatore);

        when(allenatoreRepository.findAllByEliminatoFalse()).thenReturn(allenatori);
        List<AllenatoreResponse> risultato = allenatoreService.getAllenatori("");

        assertNotNull(risultato);
        assertFalse(risultato.isEmpty());
        assertEquals(1, risultato.size());
        assertEquals(allenatori.get(0).getNome(), risultato.get(0).getNome());
    }

    @Test
    void testGetAllenatori_conNomeValido_ritornaListaAllenatoreResponse() {
        String nome = "Mario";
        List<Allenatore> allenatori = new ArrayList<>();
        allenatori.add(allenatore);
        when(allenatoreRepository.findAllByNomeContainsIgnoreCaseAndEliminatoFalse(nome)).thenReturn(allenatori);

        List<AllenatoreResponse> risultato = allenatoreService.getAllenatori(nome);

        assertNotNull(risultato);
        assertFalse(risultato.isEmpty());
        assertEquals(1, risultato.size());
        assertEquals(allenatori.get(0).getNome(), risultato.get(0).getNome());
    }

    @Test
    void testGetAllenatori_conNomeNonEsistente_ritornaListaVuota() {
        String nome = "NomeInesistente";

        when(allenatoreRepository.findAllByNomeContainsIgnoreCaseAndEliminatoFalse(nome)).thenReturn(List.of());

        List<AllenatoreResponse> risultato = allenatoreService.getAllenatori(nome);

        assertNotNull(risultato);
        assertTrue(risultato.isEmpty());
    }

}