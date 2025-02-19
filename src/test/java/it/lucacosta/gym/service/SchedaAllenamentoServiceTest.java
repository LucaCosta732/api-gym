package it.lucacosta.gym.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.request.SchedaAllenamentoRequest;
import it.lucacosta.gym.dto.response.SchedaAllenamentoResponse;
import it.lucacosta.gym.model.Allenatore;
import it.lucacosta.gym.model.Esercizio;
import it.lucacosta.gym.model.SchedaAllenamento;
import it.lucacosta.gym.model.Utente;
import it.lucacosta.gym.repository.AllenatoreRepository;
import it.lucacosta.gym.repository.EsercizioRepository;
import it.lucacosta.gym.repository.SchedaAllenamentoRepository;
import it.lucacosta.gym.repository.UtenteRepository;
import it.lucacosta.gym.service.impl.SchedaAllenamentoServiceImpl;

@SpringBootTest
public class SchedaAllenamentoServiceTest {

    @Autowired
    private SchedaAllenamentoServiceImpl schedaAllenamentoService;

    @MockitoBean
    private SchedaAllenamentoRepository schedaAllenamentoRepository;

    @MockitoBean
    private UtenteRepository utenteRepository;

    @MockitoBean
    private AllenatoreRepository allenatoreRepository;

    @MockitoBean
    private EsercizioRepository esercizioRepository;

    private SchedaAllenamento schedaAllenamento;
    private Utente utente;
    private Allenatore allenatore;
    private Esercizio esercizio;

    @BeforeEach
    void setup() {
        utente = new Utente();
        utente.setId(1L);
        utente.setNome("Luca");

        allenatore = new Allenatore();
        allenatore.setId(1L);
        allenatore.setNome("Marco");

        esercizio = new Esercizio();
        esercizio.setNome("Esercizio Spalle");
        esercizio.setAttrezzatura("Corpo");
        esercizio.setDescrizione("Esercizio per le spalle");

        schedaAllenamento = new SchedaAllenamento();
        schedaAllenamento.setId(1L);
        schedaAllenamento.setUtente(utente);
        schedaAllenamento.setAllenatore(allenatore);
        schedaAllenamento.setEsercizio(List.of(esercizio));

    }

    @Test
    void testGetSchedaAllenamentoById_idValido_ritornaSchedaAllenamentoResponse() {
        when(schedaAllenamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(schedaAllenamento));
        SchedaAllenamentoResponse response = schedaAllenamentoService.getSchedaAllenamentoById(1L);

        assertEquals(schedaAllenamento.getNome(), response.getNome());
    }

    @Test
    void testGetSchedaAllenamentoById_idNonValido_lanciaEccezione() {
        when(schedaAllenamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.empty());
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> schedaAllenamentoService.getSchedaAllenamentoById(1L));

        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatusCode());
        assertEquals("Scheda allenamento non trovata", responseStatusException.getReason());
    }

    @Test
    void testGetAllSchedeAllenamento_ritornaListaDiSchedaAllenamentoResponse() {

        List<SchedaAllenamento> schedaAllenamentoList = new ArrayList<>();
        schedaAllenamentoList.add(schedaAllenamento);
        when(schedaAllenamentoRepository.findAllByEliminatoFalse()).thenReturn(schedaAllenamentoList);

        List<SchedaAllenamentoResponse> response = schedaAllenamentoService.getAllSchedeAllenamento();

        assertFalse(response.isEmpty());
        assertEquals(1, response.size());
    }

    @Test
    void testCreateSchedaAllenamento_inputValido_ritornaSchedaAllenamentoResponse() {
        SchedaAllenamentoRequest request = new SchedaAllenamentoRequest();
        request.setNome("Scheda Test");
        request.setEsercizioID(List.of(1L));
        request.setDataFine(Date.valueOf(LocalDate.now().plusDays(30)));

        when(utenteRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(utente));
        when(allenatoreRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(allenatore));
        when(esercizioRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(esercizio));

        SchedaAllenamentoResponse response = schedaAllenamentoService.createSchedaAllenamento(1L, 1L, request);

        assertEquals(request.getNome(), response.getNome());
    }

    @Test
    void testCreateSchedaAllenamento_utenteIdNonValido_lanciaEccezione() {
        when(utenteRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.empty());
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> schedaAllenamentoService.createSchedaAllenamento(1L, 1L, new SchedaAllenamentoRequest()));

        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatusCode());
        assertEquals("Utente non trovato", responseStatusException.getReason());

    }

    @Test
    void testCreateSchedaAllenamento_allenatoreIdNonValido_lanciaEccezione() {
        when(utenteRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(utente));
        when(allenatoreRepository.findByIdAndEliminatoFalse(99L)).thenReturn(Optional.empty());
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> schedaAllenamentoService.createSchedaAllenamento(1L, 99L, new SchedaAllenamentoRequest()));

        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatusCode());
        assertEquals("Allenatore non trovato", responseStatusException.getReason());
    }

    @Test
    void testUpdateAggiungiEsercizi_inputValido_ritornaSchedaAllenamentoResponse() {

        Esercizio nuovoEsercizio = new Esercizio();
        nuovoEsercizio.setId(2L);
        nuovoEsercizio.setNome("Esercizio Gambe");

        List<Long> eserciziDaAggiungere = List.of(2L);

        when(schedaAllenamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(schedaAllenamento));
        when(esercizioRepository.findByIdAndEliminatoFalse(2L)).thenReturn(Optional.of(nuovoEsercizio));

        SchedaAllenamentoResponse response = schedaAllenamentoService.updateAggiungiEsercizi(1L, eserciziDaAggiungere);

        assertFalse(response.getEsercizio().isEmpty());
    }

    @Test
    void testUpdateAggiungiEsercizi_schedaIdNonValido_lanciaEccezione() {
        List<Long> listEsercizi = new ArrayList<>(Arrays.asList(1L, 2L));
        when(schedaAllenamentoRepository.findByIdAndEliminatoFalse(99L)).thenReturn(Optional.empty());

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> schedaAllenamentoService.updateAggiungiEsercizi(99L, listEsercizi));

        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatusCode());
        assertEquals("Scheda allenamento non trovata", responseStatusException.getReason());
    }

    @Test
    void testUpdateAggiungiEsercizi_esercizioIdNonValido_lanciaEccezione() {
        List<Long> listEsercizi = new ArrayList<>(Arrays.asList(1L, 2L));
        when(schedaAllenamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(schedaAllenamento));
        when(esercizioRepository.findByIdAndEliminatoFalse(99L)).thenReturn(Optional.empty());

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> schedaAllenamentoService.updateAggiungiEsercizi(1L, listEsercizi));

        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatusCode());
        assertEquals("Esercizio non trovato", responseStatusException.getReason());
    }

    @Test
    void testUpdateAllenatore_inputValido_ritornaSchedaAllenamentoResponse() {
        Allenatore nuovoAllenatore = new Allenatore();
        nuovoAllenatore.setId(2L);
        nuovoAllenatore.setNome("Giuseppe");

        when(schedaAllenamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(schedaAllenamento));
        when(allenatoreRepository.findByIdAndEliminatoFalse(2L)).thenReturn(Optional.of(nuovoAllenatore));

        SchedaAllenamentoResponse response = schedaAllenamentoService.updateAllenatore(1L, 2L);

        assertEquals(nuovoAllenatore.getNome(), response.getAllenatore().getNome());
    }

    @Test
    void testUpdateAllenatore_schedaIdNonValido_lanciaEccezione() {
        when(schedaAllenamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.empty());

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> schedaAllenamentoService.updateAllenatore(1L, 1L));

        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatusCode());
        assertEquals("Scheda allenamento non trovata", responseStatusException.getReason());
    }

    @Test
    void testUpdateAllenatore_allenatoreIdNonValido_lanciaEccezione() {
        when(schedaAllenamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(schedaAllenamento));
        when(allenatoreRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.empty());

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> schedaAllenamentoService.updateAllenatore(1L, 1L));

        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatusCode());
        assertEquals("Allenatore non trovato", responseStatusException.getReason());
    }

    @Test
    void testUpdateSchedaAllenamento_inputValido_ritornaSchedaAllenamentoResponse() {
        SchedaAllenamentoRequest request = new SchedaAllenamentoRequest();
        request.setNome("Scheda Aggiornata");
        request.setEsercizioID(List.of(1L));
        request.setDataFine(Date.valueOf(LocalDate.now().plusDays(45)));

        when(schedaAllenamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(schedaAllenamento));
        when(esercizioRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(esercizio));

        SchedaAllenamentoResponse response = schedaAllenamentoService.updateSchedaAllenamento(request, 1L);

        assertEquals(request.getNome(), response.getNome());
        assertEquals(request.getDataFine(), response.getDataFine());
    }

    @Test
    void testUpdateSchedaAllenamento_schedaIdNonValido_lanciaEccezione() {
        SchedaAllenamentoRequest request = new SchedaAllenamentoRequest();
        request.setNome("Scheda Aggiornata");
        request.setEsercizioID(List.of(1L));

        when(schedaAllenamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.empty());

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> schedaAllenamentoService.updateSchedaAllenamento(request, 1L));

        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatusCode());
        assertEquals("Scheda allenamento non trovata", responseStatusException.getReason());
    }

    @Test
    void testUpdateSchedaAllenamento_esercizioIdNonValido_lanciaEccezione() {
        when(schedaAllenamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(schedaAllenamento));
        when(esercizioRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.empty());

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> schedaAllenamentoService.updateSchedaAllenamento(new SchedaAllenamentoRequest("Mimmo",
                        Arrays.asList(1L, 2L), Date.valueOf(LocalDate.now().plusDays(40))), 1L));

        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatusCode());
        assertEquals("Esercizio non trovato", responseStatusException.getReason());
    }

    @Test
    void testDeleteSchedaAllenamento_idValido_ritornaTrue() {
        when(schedaAllenamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(schedaAllenamento));

        boolean result = schedaAllenamentoService.deleteSchedaAllenamento(1L);

        assertTrue(result);
    }

    @Test
    void testDeleteSchedaAllenamento_idNonValido_lanciaEccezione() {
        when(schedaAllenamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.empty());
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> schedaAllenamentoService.deleteSchedaAllenamento(1L));

        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatusCode());
        assertEquals("Scheda allenamento non trovata", responseStatusException.getReason());
    }

}
