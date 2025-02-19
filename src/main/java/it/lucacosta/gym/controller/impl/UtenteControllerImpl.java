package it.lucacosta.gym.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lucacosta.gym.controller.UtenteController;
import it.lucacosta.gym.dto.request.UtenteRequest;
import it.lucacosta.gym.dto.response.UtenteResponse;
import it.lucacosta.gym.service.UtenteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/utente")
@RequiredArgsConstructor
@Validated
public class UtenteControllerImpl implements UtenteController {

    private final UtenteService utenteService;

    @Override
    public ResponseEntity<UtenteResponse> getUtenteById(Long id) {
        return new ResponseEntity<UtenteResponse>(utenteService.getUtenteById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UtenteResponse>> getUtenti(String name) {
        return new ResponseEntity<List<UtenteResponse>>(utenteService.getUtenti(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UtenteResponse>> addUtenti(List<UtenteRequest> utente) {
        return new ResponseEntity<List<UtenteResponse>>(utenteService.addUtenti(utente), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UtenteResponse> addUtente(UtenteRequest utente) {
        return new ResponseEntity<UtenteResponse>(utenteService.addUtente(utente), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UtenteResponse> updateUtente(UtenteRequest utente, Long id) {
        return new ResponseEntity<UtenteResponse>(utenteService.updateUtente(utente, id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteUtente(Long id) {
        return new ResponseEntity<Boolean>(utenteService.deleteUtente(id), HttpStatus.OK);
    }

}
