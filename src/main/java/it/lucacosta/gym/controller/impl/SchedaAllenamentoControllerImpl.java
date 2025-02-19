package it.lucacosta.gym.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lucacosta.gym.controller.SchedaAllenamentoController;
import it.lucacosta.gym.dto.request.SchedaAllenamentoRequest;
import it.lucacosta.gym.dto.response.SchedaAllenamentoResponse;
import it.lucacosta.gym.service.SchedaAllenamentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/schedaAllenamento")
@RequiredArgsConstructor
public class SchedaAllenamentoControllerImpl implements SchedaAllenamentoController {

    private final SchedaAllenamentoService schedaAllenamentoService;

    @Override
    public ResponseEntity<SchedaAllenamentoResponse> getSchedaAllenamentoById(Long id) {
        return new ResponseEntity<SchedaAllenamentoResponse>(
                schedaAllenamentoService.getSchedaAllenamentoById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SchedaAllenamentoResponse>> getAllSchedeAllenamento() {
        return new ResponseEntity<List<SchedaAllenamentoResponse>>(
                schedaAllenamentoService.getAllSchedeAllenamento(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SchedaAllenamentoResponse> createSchedaAllenamento(Long utenteId, Long allenatoreId,
            SchedaAllenamentoRequest schedaAllenamentoRequest) {
        return new ResponseEntity<SchedaAllenamentoResponse>(
                schedaAllenamentoService.createSchedaAllenamento(utenteId, allenatoreId, schedaAllenamentoRequest),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SchedaAllenamentoResponse> updateEserciziDaAggiungere(Long id,
            List<Long> eserciziDaAggiungere) {
        return new ResponseEntity<SchedaAllenamentoResponse>(
                schedaAllenamentoService.updateAggiungiEsercizi(id, eserciziDaAggiungere), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SchedaAllenamentoResponse> updateAllenatore(Long id, Long nuovoAllenatoreId) {
        return new ResponseEntity<SchedaAllenamentoResponse>(
                schedaAllenamentoService.updateAllenatore(id, nuovoAllenatoreId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SchedaAllenamentoResponse> updateSchedaAllenamento(SchedaAllenamentoRequest schedaAllenamento,
            Long id) {
        return new ResponseEntity<SchedaAllenamentoResponse>(
                schedaAllenamentoService.updateSchedaAllenamento(schedaAllenamento, id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteSchedaAllenamento(Long id) {
        return new ResponseEntity<Boolean>(schedaAllenamentoService.deleteSchedaAllenamento(id), HttpStatus.OK);
    }

}
