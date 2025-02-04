package it.lucacosta.gym.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lucacosta.gym.controller.EsercizioController;
import it.lucacosta.gym.dto.EsercizioDto;
import it.lucacosta.gym.service.EsercizioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/esercizi")
@RequiredArgsConstructor
@Service
public class EsercizioControllerImpl implements EsercizioController {

    private final EsercizioService esercizioService;

    @Override
    public ResponseEntity<EsercizioDto> getEsercizioById(Long id) {
        return new ResponseEntity<EsercizioDto>(esercizioService.getEsercizioById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EsercizioDto>> getEsercizi() {
        return new ResponseEntity<List<EsercizioDto>>(esercizioService.getEsercizi(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EsercizioDto> addEsercizio(EsercizioDto esercizioDto) {
        return new ResponseEntity<EsercizioDto>(esercizioService.addEsercizio(esercizioDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EsercizioDto>> addEsercizi(List<EsercizioDto> eserciziDto) {
        return new ResponseEntity<List<EsercizioDto>>(esercizioService.addEsercizi(eserciziDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EsercizioDto> updateEsercizio(EsercizioDto esercizioDto) {
        return new ResponseEntity<EsercizioDto>(esercizioService.updateEsercizio(esercizioDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteEsercizio(Long id) {
        return new ResponseEntity<Boolean>(esercizioService.deleteEsercizio(id), HttpStatus.OK);
    }

}
