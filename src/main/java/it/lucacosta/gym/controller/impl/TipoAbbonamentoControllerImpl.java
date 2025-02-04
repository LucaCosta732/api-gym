package it.lucacosta.gym.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lucacosta.gym.controller.TipoAbbonamentoController;
import it.lucacosta.gym.dto.TipoAbbonamentoDto;
import it.lucacosta.gym.service.TipoAbbonamentoService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/v1/tipoAbbonamento")
@RestController
@RequiredArgsConstructor
public class TipoAbbonamentoControllerImpl implements TipoAbbonamentoController {

    private final TipoAbbonamentoService tipoAbbonamentoService;

    @Override
    public ResponseEntity<TipoAbbonamentoDto> getTipoAbbonamentoById(Long id) {
        return new ResponseEntity<TipoAbbonamentoDto>(tipoAbbonamentoService.getTipoAbbonamentoById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TipoAbbonamentoDto>> getTipiAbbonamento() {
        return new ResponseEntity<List<TipoAbbonamentoDto>>(tipoAbbonamentoService.getTipiAbbonamento(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoAbbonamentoDto> addTipoAbbonamento(TipoAbbonamentoDto tipoAbbonamento) {
        return new ResponseEntity<TipoAbbonamentoDto>(tipoAbbonamentoService.addTipoAbbonamento(tipoAbbonamento),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TipoAbbonamentoDto> updateTipoAbbonamento(TipoAbbonamentoDto tipoAbbonamento) {
        return new ResponseEntity<TipoAbbonamentoDto>(tipoAbbonamentoService.updateTipoAbbonamento(tipoAbbonamento),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteTipoAbbonamento(Long id) {
        return new ResponseEntity<Boolean>(tipoAbbonamentoService.deleteTipoAbbonamento(id), HttpStatus.OK);
    }

}
