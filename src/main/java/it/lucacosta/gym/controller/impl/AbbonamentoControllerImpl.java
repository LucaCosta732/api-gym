package it.lucacosta.gym.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lucacosta.gym.controller.AbbonamentoController;
import it.lucacosta.gym.dto.AbbonamentoDto;
import it.lucacosta.gym.service.AbbonamentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/abbonamento")
@RequiredArgsConstructor
public class AbbonamentoControllerImpl implements AbbonamentoController {
        
    private final AbbonamentoService abbonamentoService;

    @Override
    public ResponseEntity<AbbonamentoDto> getAbbonamentoById(Long id) {
        return new ResponseEntity<AbbonamentoDto>(abbonamentoService.getAbbonamentoById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AbbonamentoDto>> getAbbonamenti() {
        return new ResponseEntity<List<AbbonamentoDto>>(abbonamentoService.getAbbonamenti(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AbbonamentoDto> addAbbonamento(AbbonamentoDto abbonamento) {
        return new ResponseEntity<AbbonamentoDto>(abbonamentoService.addAbbonamento(abbonamento), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AbbonamentoDto> updateAbbonamento(AbbonamentoDto abbonamento) {
        return new ResponseEntity<AbbonamentoDto>(abbonamentoService.updateAbbonamento(abbonamento), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteAbbonamento(Long id) {
        return new ResponseEntity<Boolean>(abbonamentoService.deleteAbbonamento(id), HttpStatus.OK);
    }

}
