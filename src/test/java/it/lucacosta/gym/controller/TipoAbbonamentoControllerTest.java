package it.lucacosta.gym.controller;


import it.lucacosta.gym.dto.request.TipoAbbonamentoRequest;
import it.lucacosta.gym.dto.response.TipoAbbonamentoResponse;
import it.lucacosta.gym.model.Tipo;
import it.lucacosta.gym.service.TipoAbbonamentoService;
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

@SpringBootTest
@SuppressWarnings("null")
public class TipoAbbonamentoControllerTest {

    @Autowired
    private TipoAbbonamentoController tipoAbbonamentoController;

    @MockitoBean
    private TipoAbbonamentoService tipoAbbonamentoService;

    private TipoAbbonamentoResponse tipoAbbonamentoTest;
    private TipoAbbonamentoRequest tipoAbbonamentoRequest;
    private List<TipoAbbonamentoResponse> tipiAbbonamentoTestList;

    @BeforeEach
    void setup() {
        tipoAbbonamentoTest = new TipoAbbonamentoResponse();
        tipoAbbonamentoTest.setId(1L);
        tipoAbbonamentoTest.setNome(Tipo.MENSILE);
        tipoAbbonamentoTest.setDescrizione("Abbonamento mensile standard");
        tipoAbbonamentoTest.setPrezzo(29.99);

        tipiAbbonamentoTestList = new ArrayList<>();
        tipiAbbonamentoTestList.add(tipoAbbonamentoTest);

        tipoAbbonamentoRequest = new TipoAbbonamentoRequest();
        tipoAbbonamentoRequest.setNome(Tipo.ANNUALE);
        tipoAbbonamentoRequest.setDescrizione("Abbonamento trimestrale scontato");
        tipoAbbonamentoRequest.setPrezzo(79.99);
    }

    @Test
    void testGetTipoAbbonamentoById_inputValidi_ritornaOK() {
        when(tipoAbbonamentoService.getTipoAbbonamentoById(1L)).thenReturn(tipoAbbonamentoTest);

        ResponseEntity<TipoAbbonamentoResponse> risultato = tipoAbbonamentoController.getTipoAbbonamentoById(1L);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(tipoAbbonamentoTest.getId(), risultato.getBody().getId());
        assertEquals(tipoAbbonamentoTest.getNome(), risultato.getBody().getNome());
        assertEquals(tipoAbbonamentoTest.getDescrizione(), risultato.getBody().getDescrizione());
        assertEquals(tipoAbbonamentoTest.getPrezzo(), risultato.getBody().getPrezzo());
    }

    @Test
    void testGetTipiAbbonamento_ritornaOK() {
        when(tipoAbbonamentoService.getTipiAbbonamento()).thenReturn(tipiAbbonamentoTestList);

        ResponseEntity<List<TipoAbbonamentoResponse>> risultato = tipoAbbonamentoController.getTipiAbbonamento();

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertFalse(risultato.getBody().isEmpty());
        assertEquals(1, risultato.getBody().size());
        assertEquals(tipiAbbonamentoTestList.get(0).getNome(), risultato.getBody().get(0).getNome());
    }

    @Test
    void testGetTipiAbbonamento_listaVuota_ritornaOK() {
        when(tipoAbbonamentoService.getTipiAbbonamento()).thenReturn(Collections.emptyList());

        ResponseEntity<List<TipoAbbonamentoResponse>> risultato = tipoAbbonamentoController.getTipiAbbonamento();

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertTrue(risultato.getBody().isEmpty());
    }

    @Test
    void testAddTipoAbbonamento_inputValidi_ritornaOK() {
        when(tipoAbbonamentoService.addTipoAbbonamento(tipoAbbonamentoRequest)).thenReturn(tipoAbbonamentoTest);

        ResponseEntity<TipoAbbonamentoResponse> risultato = tipoAbbonamentoController.addTipoAbbonamento(tipoAbbonamentoRequest);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(tipoAbbonamentoTest.getNome(), risultato.getBody().getNome());
        assertEquals(tipoAbbonamentoTest.getDescrizione(), risultato.getBody().getDescrizione());
        assertEquals(tipoAbbonamentoTest.getPrezzo(), risultato.getBody().getPrezzo());
    }

    @Test
    void testUpdateTipoAbbonamento_inputValidi_ritornaOK() {
        Long idDaAggiornare = 1L;
        when(tipoAbbonamentoService.updateTipoAbbonamento(tipoAbbonamentoRequest, idDaAggiornare)).thenReturn(tipoAbbonamentoTest);

        ResponseEntity<TipoAbbonamentoResponse> risultato = tipoAbbonamentoController.updateTipoAbbonamento(tipoAbbonamentoRequest, idDaAggiornare);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertEquals(tipoAbbonamentoTest.getId(), risultato.getBody().getId());
        assertEquals(tipoAbbonamentoTest.getNome(), risultato.getBody().getNome());
        assertEquals(tipoAbbonamentoTest.getDescrizione(), risultato.getBody().getDescrizione());
        assertEquals(tipoAbbonamentoTest.getPrezzo(), risultato.getBody().getPrezzo());
    }

    @Test
    void testDeleteTipoAbbonamento_inputValidi_ritornaOK() {
        Long idDaCancellare = 1L;
        when(tipoAbbonamentoService.deleteTipoAbbonamento(idDaCancellare)).thenReturn(true);

        ResponseEntity<Boolean> risultato = tipoAbbonamentoController.deleteTipoAbbonamento(idDaCancellare);

        assertEquals(HttpStatus.OK, risultato.getStatusCode());
        assertNotNull(risultato.getBody());
        assertTrue(risultato.getBody());
    }
}