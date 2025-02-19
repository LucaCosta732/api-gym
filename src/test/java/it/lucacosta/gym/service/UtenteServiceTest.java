package it.lucacosta.gym.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.request.UtenteRequest;
import it.lucacosta.gym.dto.response.UtenteResponse;
import it.lucacosta.gym.model.Utente;
import it.lucacosta.gym.repository.UtenteRepository;
import it.lucacosta.gym.service.impl.UtenteServiceImpl;

@SpringBootTest
public class UtenteServiceTest {

    @Autowired
    private UtenteServiceImpl utenteService;

    @MockitoBean
    private UtenteRepository utenteRepository;

    private Utente utente;
    private UtenteRequest utenteRequest;
    private UtenteRequest utenteUpdateRequest;

    @BeforeEach
    void setup() {
        utente = new Utente();
        utente.setId(1L);
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setEmail("mario.rossi@example.com");
        utente.setPassword("password123");
        utente.setDataIscrizione(Date.valueOf(LocalDate.of(2025, 1, 18)));
        utente.setTelefono("393333333333");
        utente.setEliminato(false);

        utenteRequest = new UtenteRequest();
        utenteRequest.setNome("Mario");
        utenteRequest.setCognome("Rossi");
        utenteRequest.setEmail("mario.rossi@example.com");
        utenteRequest.setPassword("password123");
        utenteRequest.setDataIscrizione(Date.valueOf(LocalDate.of(2025, 1, 18)));
        utenteRequest.setTelefono("393333333333");

        utenteUpdateRequest = new UtenteRequest();
        utenteUpdateRequest.setNome("NomeAggiornato");
        utenteUpdateRequest.setCognome("CognomeAggiornato");
        utenteUpdateRequest.setEmail("email.aggiornata@example.com");
        utenteUpdateRequest.setPassword("passwordAggiornata");
        utenteUpdateRequest.setDataIscrizione(Date.valueOf(LocalDate.of(2026, 2, 20)));
        utenteUpdateRequest.setTelefono("3441122334");
    }

    @Test
    void testAddUtenti_inputValidi_RitornoListUtenteResponse() {
        List<UtenteRequest> utenteRequestList = new ArrayList<>();
        utenteRequestList.add(utenteRequest);

        List<Utente> utentiSalvati = new ArrayList<>();
        utentiSalvati.add(utente);

        when(utenteRepository.saveAll(any())).thenReturn(utentiSalvati);

        List<UtenteResponse> risultato = utenteService.addUtenti(utenteRequestList);

        assertNotNull(risultato);
        assertEquals(utenteRequestList.size(), risultato.size());
        assertEquals(utente.getNome(), risultato.get(0).getNome());
    }

    @Test
    void testAddUtente_inputValidi_RitornoUtenteResponse() {
        when(utenteRepository.save(any(Utente.class))).thenReturn(utente);

        UtenteResponse risultato = utenteService.addUtente(utenteRequest);

        assertNotNull(risultato);
        assertEquals(utente.getNome(), risultato.getNome());
        assertEquals(utente.getCognome(), risultato.getCognome());
        assertEquals(utente.getEmail(), risultato.getEmail());
    }

    @Test
    void testUpdateUtente_inputValidi_RitornoUtenteResponse() {
        Long id = 1L;
        Utente utenteAggiornato = new Utente();
        utenteAggiornato.setId(id);
        utenteAggiornato.setNome(utenteUpdateRequest.getNome());
        utenteAggiornato.setCognome(utenteUpdateRequest.getCognome());
        utenteAggiornato.setEmail(utenteUpdateRequest.getEmail());
        utenteAggiornato.setPassword(utenteUpdateRequest.getPassword());
        utenteAggiornato.setDataIscrizione(utenteUpdateRequest.getDataIscrizione());
        utenteAggiornato.setTelefono(utenteUpdateRequest.getTelefono());

        when(utenteRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.of(utente));
        when(utenteRepository.save(any(Utente.class))).thenReturn(utenteAggiornato);

        UtenteResponse risultato = utenteService.updateUtente(utenteUpdateRequest, id);

        assertNotNull(risultato);
        assertEquals(utenteUpdateRequest.getNome(), risultato.getNome());
        assertEquals(utenteUpdateRequest.getCognome(), risultato.getCognome());
        assertEquals(utenteUpdateRequest.getEmail(), risultato.getEmail());
        assertEquals(utenteUpdateRequest.getDataIscrizione(), risultato.getDataIscrizione());
        assertEquals(utenteUpdateRequest.getTelefono(), risultato.getTelefono());
    }

    @Test
    void testDeleteUtente_inputValidi_RitornoTrue() {
        Long id = 1L;
        Utente utenteEliminato = new Utente();
        utenteEliminato.setId(id);
        utenteEliminato.setEliminato(true);

        when(utenteRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.of(utente));
        when(utenteRepository.save(any(Utente.class))).thenReturn(utenteEliminato);

        Boolean risultato = utenteService.deleteUtente(id);

        assertTrue(risultato);
    }

    @Test
    void testDeleteUtente_inputInvalidi_ThrowException_UtenteNonTrovato() {
        Long id = 6L;
        when(utenteRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.empty());

        ResponseStatusException risultato = assertThrows(ResponseStatusException.class,
                () -> utenteService.deleteUtente(id));

        assertEquals(HttpStatus.NOT_FOUND, risultato.getStatusCode());
        assertEquals("Utente non trovato", risultato.getReason());
    }

    @Test
    void testGetUtenteById_InputValidi_RitornoUtenteResponse() {
        Long id = 1L;
        when(utenteRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.of(utente));

        UtenteResponse risultato = utenteService.getUtenteById(id);

        assertNotNull(risultato);
        assertEquals(utente.getNome(), risultato.getNome());
        assertEquals(utente.getCognome(), risultato.getCognome());
        assertEquals(utente.getEmail(), risultato.getEmail());
        assertEquals(utente.getDataIscrizione(), risultato.getDataIscrizione());
        assertEquals(utente.getTelefono(), risultato.getTelefono());
    }

    @Test
    void testGetUtenteById_InputInvalidi_ThrowException_UtenteNonTrovato() {
        Long id = 999L;
        when(utenteRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            utenteService.getUtenteById(id);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Utente non trovato", exception.getReason());
    }

    @Test
    void testGetUtenti_nomeNonSpecificato_RitornaTuttiUtenti() {
        List<Utente> utentiList = new ArrayList<>();
        utentiList.add(utente);

        when(utenteRepository.findAllByEliminatoFalse()).thenReturn(utentiList);

        List<UtenteResponse> risultati = utenteService.getUtenti(null);

        assertNotNull(risultati);
        assertFalse(risultati.isEmpty());
        assertEquals(utentiList.size(), risultati.size());
        assertEquals(utente.getNome(), risultati.get(0).getNome());
    }

    @Test
    void testGetUtenti_blank_RitornaTuttiUtenti() {
        List<Utente> utentiList = new ArrayList<>();
        utentiList.add(utente);

        when(utenteRepository.findAllByEliminatoFalse()).thenReturn(utentiList);

        List<UtenteResponse> risultati = utenteService.getUtenti("");

        assertNotNull(risultati);
        assertFalse(risultati.isEmpty());
        assertEquals(utentiList.size(), risultati.size());
        assertEquals(utente.getNome(), risultati.get(0).getNome());
    }

    @Test
    void testGetUtenti_nomeSpecificato_RitornaUtentiCorrispondenti() {
        String nomeRicerca = "Mario";
        List<Utente> utentiList = new ArrayList<>();
        utentiList.add(utente);

        when(utenteRepository.findByNomeContainingIgnoreCaseAndEliminatoFalse(anyString())).thenReturn(utentiList);

        List<UtenteResponse> risultati = utenteService.getUtenti(nomeRicerca);

        assertNotNull(risultati);
        assertFalse(risultati.isEmpty());
        assertEquals(utentiList.size(), risultati.size());
        assertEquals(utente.getNome(), risultati.get(0).getNome());
    }

    @Test
    void testGetUtenti_nomeSpecificato_NessunUtenteTrovato_RitornaListaVuota() {
        String nomeRicerca = "NomeNonEsistente";
        when(utenteRepository.findByNomeContainingIgnoreCaseAndEliminatoFalse(nomeRicerca))
                .thenReturn(Collections.emptyList());

        List<UtenteResponse> risultati = utenteService.getUtenti(nomeRicerca);

        assertNotNull(risultati);
        assertTrue(risultati.isEmpty());
    }
}