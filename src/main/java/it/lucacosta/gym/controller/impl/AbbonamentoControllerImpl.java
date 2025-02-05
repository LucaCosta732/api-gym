package it.lucacosta.gym.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lucacosta.gym.controller.AbbonamentoController;
import it.lucacosta.gym.dto.response.AbbonamentoResponse;
import it.lucacosta.gym.model.Abbonamento.Stato;
import it.lucacosta.gym.service.AbbonamentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/abbonamento")
@RequiredArgsConstructor
public class AbbonamentoControllerImpl implements AbbonamentoController {

    private final AbbonamentoService abbonamentoService;

    @Override
    public ResponseEntity<AbbonamentoResponse> getAbbonamentoById(Long id) {
        return new ResponseEntity<AbbonamentoResponse>(abbonamentoService.getAbbonamentoById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AbbonamentoResponse>> getAbbonamenti(Long userId) {
        return new ResponseEntity<List<AbbonamentoResponse>>(abbonamentoService.getAbbonamenti(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AbbonamentoResponse> addAbbonamento(Long idTipo, Long idUtente) {
        return new ResponseEntity<AbbonamentoResponse>(abbonamentoService.addAbbonamento(idTipo, idUtente),
                HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Stato> controlloValiditaAbbonamento(Long id) {
        return new ResponseEntity<Stato>(abbonamentoService.controlloValidita(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteAbbonamento(Long id) {
        return new ResponseEntity<Boolean>(abbonamentoService.deleteAbbonamento(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AbbonamentoResponse>> controlloAbbonamentiScaduti() {
        return new ResponseEntity<List<AbbonamentoResponse>>(abbonamentoService.controlloAbbonamentiScaduti(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AbbonamentoResponse> updateAbbonamento(Long id, Long idTipoAbbonamento) {
        return new ResponseEntity<AbbonamentoResponse>(
                abbonamentoService.updateAbbonamento(id, idTipoAbbonamento), HttpStatus.OK);
    }
}
