package it.lucacosta.gym.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.response.AbbonamentoResponse;
import it.lucacosta.gym.model.Abbonamento;
import it.lucacosta.gym.model.Stato;
import it.lucacosta.gym.model.Tipo;
import it.lucacosta.gym.model.TipoAbbonamento;
import it.lucacosta.gym.model.Utente;
import it.lucacosta.gym.repository.AbbonamentoRepository;
import it.lucacosta.gym.repository.TipoAbbonamentoRepository;
import it.lucacosta.gym.repository.UtenteRepository;

@SpringBootTest
public class AbbonamentoServiceTest {

    @Autowired
    private AbbonamentoService abbonamentoService;

    @MockitoBean
    private AbbonamentoRepository abbonamentoRepository;

    @MockitoBean
    private TipoAbbonamentoRepository tipoAbbonamentoRepository;

    @MockitoBean
    private UtenteRepository utenteRepository;

    private Utente utente;
    private TipoAbbonamento tipoAbbonamento;
    private Abbonamento abbonamento;

    @BeforeEach
    void setup() {

        utente = new Utente();
        utente.setId(1L);
        utente.setNome("Luca");

        tipoAbbonamento = new TipoAbbonamento();
        tipoAbbonamento.setId(1L);
        tipoAbbonamento.setNome(Tipo.MENSILE);

        abbonamento = new Abbonamento();
        abbonamento.setId(1L);
        abbonamento.setUtente(utente);
        abbonamento.setTipo(tipoAbbonamento);
        abbonamento.setDataInizio(Date.valueOf(LocalDate.now()));
        abbonamento.setDataFine(Date.valueOf(LocalDate.now().plusDays(30)));
        abbonamento.setStato(Stato.ATTIVO);
    }

    @ParameterizedTest
    @EnumSource(Tipo.class)
    void testAddAbbonamento_inputValido_ritornaAbbonamentoResponse(Tipo tipo) {

        // Controlla la data di fine in base al tipo di abbonamento
        int giorniAttesi = switch (tipo) {
            case MENSILE -> 30;
            case SEMESTRALE -> 180;
            case ANNUALE -> 380;
        };

        abbonamento.setDataFine(Date.valueOf(LocalDate.now().plusDays(giorniAttesi)));
        tipoAbbonamento.setNome(tipo);

        // Mock dei repository
        when(utenteRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(utente));
        when(tipoAbbonamentoRepository.findByIdAndEliminatoFalse(1L))
                .thenReturn(Optional.of(tipoAbbonamento));
        when(abbonamentoRepository.save(any(Abbonamento.class))).thenReturn(abbonamento);

        // Act
        AbbonamentoResponse risultato = abbonamentoService.addAbbonamento(1L, 1L);

        // Assert
        assertNotNull(risultato);
        assertEquals(tipo, risultato.getTipo().getNome()); // Controlla il tipo corretto

        assertEquals(Date.valueOf(LocalDate.now().plusDays(giorniAttesi)), risultato.getDataFine());

    }

    @Test
    void testDeleteAbbonamento_inputValido_restituisceTrue() {
        when(abbonamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(abbonamento));

        boolean risultato = abbonamentoService.deleteAbbonamento(1L);

        assertTrue(risultato);
    }

    @Test
    void testDeleteAbbonamento_abbonamentoNonTrovato_lanciaEccezione() {
        when(abbonamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> abbonamentoService.deleteAbbonamento(1L));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Abbonamento non trovato", exception.getReason());
    }

    @Test
    void testGetAbbonamentoById_inputValido_ritornaAbbonamentoResponse() {
        when(abbonamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(abbonamento));

        AbbonamentoResponse response = abbonamentoService.getAbbonamentoById(1L);

        assertNotNull(response);
        assertEquals(abbonamento.getStato(), response.getStato());
    }

    @Test
    void testGetAbbonamentoById_abbonamentoNonTrovato_lanciaEccezione() {
        when(abbonamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> abbonamentoService.getAbbonamentoById(1L));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Abbonamento non trovato", exception.getReason());
    }

    @Test
    void testGetAbbonamenti_senzaUserId_ritornaListaCompleta() {
        List<Abbonamento> list = new ArrayList<>();
        list.add(abbonamento);
        when(utenteRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(utente));
        when(abbonamentoRepository.findByUtente(utente)).thenReturn(list);

        List<AbbonamentoResponse> listResponse = abbonamentoService.getAbbonamenti(null);
        assertNotNull(listResponse);
    }

    @Test
    void testGetAbbonamenti_conUserId_ritornaListaFiltrata() {
        List<Abbonamento> list = new ArrayList<>();
        list.add(abbonamento);
        when(utenteRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(utente));
        when(abbonamentoRepository.findAllByEliminatoFalse()).thenReturn(list);

        List<AbbonamentoResponse> listResponse = abbonamentoService.getAbbonamenti(1l);
        assertNotNull(listResponse);
    }

    @Test
    void testControlloValidita_abbonamentoAncoraValido_restituisceStatoAttivo() {
        when(abbonamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(abbonamento));
        Stato valido = abbonamentoService.controlloValidita(1L);
        assertEquals(Stato.ATTIVO, valido);
    }

    @Test
    void testControlloValidita_abbonamentoScaduto_restituisceStatoScaduto() {
        abbonamento.setDataFine(Date.valueOf(LocalDate.now().minusDays(1)));
        when(abbonamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(abbonamento));
        Stato valido = abbonamentoService.controlloValidita(1L);
        assertEquals(Stato.SCADUTO, valido);
    }

    @Test
    void testControlloAbbonamentiScaduti_restituisceListaAbbonamentiScaduti() {
        when(abbonamentoRepository.findAllWithAbbonamentoScaduto()).thenReturn(List.of(abbonamento));

        List<AbbonamentoResponse> responses = abbonamentoService.controlloAbbonamentiScaduti();
        assertFalse(responses.isEmpty());
    }

    @Test
    void testAttivaAbbonamento_inputValido_ritornaNuovoAbbonamentoResponse() {
        when(abbonamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(abbonamento));
        when(abbonamentoRepository.save(any(Abbonamento.class))).thenReturn(abbonamento);
        when(tipoAbbonamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.of(tipoAbbonamento));

        AbbonamentoResponse response = abbonamentoService.attivaAbbonamento(1L, 1L);
        System.out.println(response);
        assertEquals(Stato.ATTIVO, response.getStato());
    }

    @Test
    void testAttivaAbbonamento_abbonamentoNonTrovato_lanciaEccezione() {
        when(abbonamentoRepository.findByIdAndEliminatoFalse(1L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> abbonamentoService.attivaAbbonamento(1L, 1L));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Tipo abbonamento non trovato", exception.getReason());
    }

}
