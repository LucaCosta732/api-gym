package it.lucacosta.gym.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lucacosta.gym.controller.EsercizioController;
import it.lucacosta.gym.dto.request.EsercizioRequest;
import it.lucacosta.gym.dto.response.EsercizioResponse;
import it.lucacosta.gym.service.EsercizioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/esercizi")
@RequiredArgsConstructor
@Service
public class EsercizioControllerImpl implements EsercizioController {

    private final EsercizioService esercizioService;

    @Override
    public ResponseEntity<EsercizioResponse> getEsercizioById(Long id) {
        return new ResponseEntity<EsercizioResponse>(esercizioService.getEsercizioById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EsercizioResponse>> getEsercizi() {
        return new ResponseEntity<List<EsercizioResponse>>(esercizioService.getEsercizi(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EsercizioResponse> addEsercizio(EsercizioRequest esercizioDto) {
        return new ResponseEntity<EsercizioResponse>(esercizioService.addEsercizio(esercizioDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EsercizioResponse>> addEsercizi(List<EsercizioRequest> eserciziDto) {
        return new ResponseEntity<List<EsercizioResponse>>(esercizioService.addEsercizi(eserciziDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EsercizioResponse> updateEsercizio(Long id, EsercizioRequest esercizioDto) {
       return new ResponseEntity<EsercizioResponse>(esercizioService.updateEsercizio(id, esercizioDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteEsercizio(Long id) {
        return new ResponseEntity<Boolean>(esercizioService.deleteEsercizio(id), HttpStatus.OK);
    }

  

}
