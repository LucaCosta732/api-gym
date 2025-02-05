package it.lucacosta.gym.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lucacosta.gym.controller.TipoAbbonamentoController;
import it.lucacosta.gym.dto.request.TipoAbbonamentoRequest;
import it.lucacosta.gym.dto.response.TipoAbbonamentoResponse;
import it.lucacosta.gym.service.TipoAbbonamentoService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/v1/tipoAbbonamento")
@RestController
@RequiredArgsConstructor
public class TipoAbbonamentoControllerImpl implements TipoAbbonamentoController {

    private final TipoAbbonamentoService tipoAbbonamentoService;

    @Override
    public ResponseEntity<TipoAbbonamentoResponse> getTipoAbbonamentoById(Long id) {
        return new ResponseEntity<TipoAbbonamentoResponse>(tipoAbbonamentoService.getTipoAbbonamentoById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TipoAbbonamentoResponse>> getTipiAbbonamento() {
        return new ResponseEntity<List<TipoAbbonamentoResponse>>(tipoAbbonamentoService.getTipiAbbonamento(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoAbbonamentoResponse> addTipoAbbonamento(TipoAbbonamentoRequest tipoAbbonamento) {
        return new ResponseEntity<TipoAbbonamentoResponse>(tipoAbbonamentoService.addTipoAbbonamento(tipoAbbonamento),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoAbbonamentoResponse> updateTipoAbbonamento(TipoAbbonamentoRequest tipoAbbonamento,
            Long id) {
        return new ResponseEntity<TipoAbbonamentoResponse>(
                tipoAbbonamentoService.updateTipoAbbonamento(tipoAbbonamento, id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteTipoAbbonamento(Long id) {
        return new ResponseEntity<Boolean>(tipoAbbonamentoService.deleteTipoAbbonamento(id), HttpStatus.OK);
    }

}
