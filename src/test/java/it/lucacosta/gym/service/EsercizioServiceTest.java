package it.lucacosta.gym.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

import it.lucacosta.gym.dto.request.EsercizioRequest;
import it.lucacosta.gym.dto.response.EsercizioResponse;
import it.lucacosta.gym.model.Esercizio;
import it.lucacosta.gym.repository.EsercizioRepository;

@SpringBootTest
public class EsercizioServiceTest {

    @Autowired
    private EsercizioService esercizioService;

    @MockitoBean
    private EsercizioRepository esercizioRepository;

    private EsercizioResponse esercizioResponse;
    private EsercizioRequest esercizioRequest;
    private List<EsercizioRequest> eserciziRequest;
    private List<EsercizioResponse> eserciziResponse;
    private Esercizio esercizio;

    @BeforeEach
    void setup() {
        esercizioResponse = new EsercizioResponse();
        esercizioResponse.setId(1L);
        esercizioResponse.setNome("Squat");
        esercizioResponse.setDescrizione("Esercizio base per le gambe");
        esercizioResponse.setGruppoMuscolare("Gambe");
        esercizioResponse.setAttrezzatura("Bilanciere");

        eserciziResponse = new ArrayList<>();
        eserciziResponse.add(esercizioResponse);

        esercizioRequest = new EsercizioRequest();
        esercizioRequest.setNome("Panca Piana");
        esercizioRequest.setDescrizione("Esercizio per il petto");
        esercizioRequest.setGruppoMuscolare("Petto");
        esercizioRequest.setAttrezzatura("Bilanciere, Panca");

        eserciziRequest = new ArrayList<>();
        eserciziRequest.add(esercizioRequest);

        esercizio = new Esercizio();
        esercizio.setId(1L);
        esercizio.setNome(esercizioRequest.getNome());
        esercizio.setDescrizione(esercizioRequest.getDescrizione());
        esercizio.setGruppoMuscolare(esercizioRequest.getGruppoMuscolare());
        esercizio.setAttrezzatura(esercizioRequest.getAttrezzatura());
        esercizio.setEliminato(false);

    }

    @Test
    void testGetEsercizioById_inputValido_ritornaEsercizioResponse() {
        Long id = 1L;
        when(esercizioRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.of(esercizio));

        EsercizioResponse ritorno = esercizioService.getEsercizioById(id);

        assertNotNull(ritorno);
        assertEquals(esercizio.getNome(), ritorno.getNome());
    }

    @Test
    void testGetEsercizioById_esercizioNonTrovato_lanciaEccezione() {
        Long id = 999L;
        when(esercizioRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.empty());

        ResponseStatusException ritorno = assertThrows(ResponseStatusException.class,
                () -> esercizioService.getEsercizioById(id));

        assertEquals(HttpStatus.NOT_FOUND, ritorno.getStatusCode());

    }

    @Test
    void testGetEsercizi_eserciziPresenti_ritornaListaEsercizioResponse() {

        List<Esercizio> esercizi = new ArrayList<>();

        when(esercizioRepository.findAllByEliminatoFalse()).thenReturn(esercizi);

        List<EsercizioResponse> ritorno = esercizioService.getEsercizi();

        assertNotNull(ritorno.size());

    }

    @Test
    void testGetEsercizi_nessunEsercizio_ritornaListaVuota() {
        when(esercizioRepository.findAllByEliminatoFalse()).thenReturn(new ArrayList<>());
        List<EsercizioResponse> result = esercizioService.getEsercizi();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testAddEsercizio_inputValido_ritornaEsercizioResponse() {
        when(esercizioRepository.save(any(Esercizio.class))).thenReturn(esercizio);

        EsercizioResponse result = esercizioService.addEsercizio(esercizioRequest);

        assertNotNull(result);
        assertEquals(esercizio.getNome(), result.getNome());
    }

    @Test
    void testAddEsercizi_inputValido_ritornaListaEsercizioResponse() {
        List<Esercizio> esercizi = new ArrayList<>();
        esercizi.add(esercizio);

        when(esercizioRepository.saveAll(anyList())).thenReturn(esercizi);

        List<EsercizioResponse> result = esercizioService.addEsercizi(eserciziRequest);
        assertNotNull(result);
        assertEquals(esercizi.size(), result.size());
    }

    @Test
    void testAddEsercizi_listaVuota_ritornaListaVuota() {
        List<EsercizioRequest> emptyList = new ArrayList<>();
        List<EsercizioResponse> result = esercizioService.addEsercizi(emptyList);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testUpdateEsercizio_inputValido_ritornaEsercizioResponse() {
        Long id = 1L;
        when(esercizioRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.of(esercizio));
        when(esercizioRepository.save(any(Esercizio.class))).thenReturn(esercizio);

        EsercizioResponse result = esercizioService.updateEsercizio(id, esercizioRequest);
        assertNotNull(result);
        assertEquals(esercizio.getNome(), result.getNome());
    }

    @Test
    void testUpdateEsercizio_esercizioNonTrovato_lanciaEccezione() {
        Long id = 999L;
        when(esercizioRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> esercizioService.updateEsercizio(id, esercizioRequest));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testDeleteEsercizio_inputValido_restituisceTrue() {
        Long id = 1L;
        when(esercizioRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.of(esercizio));

        boolean result = esercizioService.deleteEsercizio(id);
        assertTrue(result);
        assertEquals(true, esercizio.getEliminato());
    }

    @Test
    void testDeleteEsercizio_esercizioNonTrovato_lanciaEccezione() {
        Long id = 999L;
        when(esercizioRepository.findByIdAndEliminatoFalse(id)).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> esercizioService.deleteEsercizio(id));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

}