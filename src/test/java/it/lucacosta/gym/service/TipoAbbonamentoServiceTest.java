package it.lucacosta.gym.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

import it.lucacosta.gym.dto.request.TipoAbbonamentoRequest;
import it.lucacosta.gym.dto.response.TipoAbbonamentoResponse;
import it.lucacosta.gym.model.Tipo;
import it.lucacosta.gym.model.TipoAbbonamento;
import it.lucacosta.gym.repository.TipoAbbonamentoRepository;

@SpringBootTest
public class TipoAbbonamentoServiceTest {

    @Autowired
    private TipoAbbonamentoService tipoAbbonamentoService;

    @MockitoBean
    private TipoAbbonamentoRepository tipoAbbonamentoRepository;

    private TipoAbbonamentoResponse tipoAbbonamentoResponse;
    private TipoAbbonamentoRequest tipoAbbonamentoRequest;
    private TipoAbbonamento tipoAbbonamento;

    @BeforeEach
    void setup() {
        tipoAbbonamentoRequest = new TipoAbbonamentoRequest();
        tipoAbbonamentoRequest.setNome(Tipo.ANNUALE);
        tipoAbbonamentoRequest.setPrezzo(340D);
        tipoAbbonamentoRequest.setDescrizione("esempio");

        tipoAbbonamentoResponse = new TipoAbbonamentoResponse();
        tipoAbbonamentoResponse.setId(1l);
        tipoAbbonamentoResponse.setNome(Tipo.ANNUALE);
        tipoAbbonamentoResponse.setDescrizione("esempio");
        tipoAbbonamentoResponse.setPrezzo(340D);

        tipoAbbonamento = new TipoAbbonamento();
        tipoAbbonamento.setId(1L);
        tipoAbbonamento.setNome(Tipo.ANNUALE);
        tipoAbbonamento.setDescrizione("esempio");
        tipoAbbonamento.setPrezzo(340D);

    }

    @Test
    void testAddTipoAbbonamento_inputValidi_ritornaTipoAbbonamentoResponse() {

        when(tipoAbbonamentoRepository.save(any(TipoAbbonamento.class))).thenReturn(tipoAbbonamento);

        TipoAbbonamentoResponse risultato = tipoAbbonamentoService.addTipoAbbonamento(tipoAbbonamentoRequest);

        assertEquals(tipoAbbonamentoResponse.getNome(), risultato.getNome());
        assertEquals(tipoAbbonamentoResponse.getDescrizione(), risultato.getDescrizione());
        assertEquals(tipoAbbonamentoResponse.getPrezzo(), risultato.getPrezzo());
    }

    @Test
    void testUpdateTipoAbbonamento_inputValidi_ritornaTipoAbbonamentoResponse() {
        Long id = 1L;

        when(tipoAbbonamentoRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.of(tipoAbbonamento));

        TipoAbbonamentoResponse risultato = tipoAbbonamentoService.updateTipoAbbonamento(tipoAbbonamentoRequest, id);

        assertNotNull(risultato);
        assertEquals(tipoAbbonamentoRequest.getNome(), risultato.getNome());
        assertEquals(tipoAbbonamentoRequest.getDescrizione(), risultato.getDescrizione());
        assertEquals(tipoAbbonamentoRequest.getPrezzo(), risultato.getPrezzo());
    }

    @Test
    void testUpdateTipoAbbonamento_inputInvalidi_ritornaResponseStatusException() {
        Long id = 999L;

        when(tipoAbbonamentoRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.empty());

        ResponseStatusException risultato = assertThrows(ResponseStatusException.class,
                () -> tipoAbbonamentoService.updateTipoAbbonamento(tipoAbbonamentoRequest, id));

        assertEquals(HttpStatus.NOT_FOUND, risultato.getStatusCode());
        assertEquals("Tipo abbonamento non trovato", risultato.getReason());
    }

    @Test
    void testDeleteTipoAbbonamento_inputValidi_ritornaTipoAbbonamentoResponse() {
        Long id = 1L;
        tipoAbbonamento.setEliminato(true);

        when(tipoAbbonamentoRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.of(tipoAbbonamento));

        Boolean risultato = tipoAbbonamentoService.deleteTipoAbbonamento(id);

        assertTrue(risultato);
        assertEquals(true, tipoAbbonamento.getEliminato());
    }

    @Test
    void testGetTipoAbbonamentoById_inputValido_ritornaTipoAbbonamentoResponse() {
        Long id = 1L;

        when(tipoAbbonamentoRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.of(tipoAbbonamento));

        TipoAbbonamentoResponse risultato = tipoAbbonamentoService.getTipoAbbonamentoById(id);

        assertNotNull(risultato);
        assertEquals(id, risultato.getId());
        assertEquals(tipoAbbonamento.getNome(), risultato.getNome());
        assertEquals(tipoAbbonamento.getDescrizione(), risultato.getDescrizione());
        assertEquals(tipoAbbonamento.getPrezzo(), risultato.getPrezzo());
    }

    @Test
    void testGetTipoAbbonamentoById_inputNonValido_ritornaResponseStatusException() {
        Long id = 999L;

        when(tipoAbbonamentoRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.empty());

        ResponseStatusException risultato = assertThrows(ResponseStatusException.class,
                () -> tipoAbbonamentoService.getTipoAbbonamentoById(id));

        assertEquals(HttpStatus.NOT_FOUND, risultato.getStatusCode());
        assertEquals("Tipo abbonamento non trovato", risultato.getReason());
    }

    @Test
    void testGetTipiAbbonamento_ritornaListaTipiAbbonamentoResponse() {
        List<TipoAbbonamento> listaAbbonamenti = new ArrayList<>();
        listaAbbonamenti.add(tipoAbbonamento);

        when(tipoAbbonamentoRepository.findAllByEliminatoFalse()).thenReturn(listaAbbonamenti);

        List<TipoAbbonamentoResponse> risultato = tipoAbbonamentoService.getTipiAbbonamento();

        assertNotNull(risultato);
        assertEquals(1, risultato.size());
        assertEquals(tipoAbbonamento.getNome(), risultato.get(0).getNome());
    }

}