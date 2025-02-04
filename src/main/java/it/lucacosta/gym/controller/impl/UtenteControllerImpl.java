package it.lucacosta.gym.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lucacosta.gym.controller.UtenteController;
import it.lucacosta.gym.dto.UtenteDto;
import it.lucacosta.gym.service.UtenteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/utente")
@RequiredArgsConstructor
public class UtenteControllerImpl implements UtenteController {

    private final UtenteService utenteService;

    @Override
    public ResponseEntity<UtenteDto> getUtenteById(Long id) {
        return new ResponseEntity<UtenteDto>(utenteService.getUtenteById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UtenteDto>> getUtenti(String name) {
        return new ResponseEntity<List<UtenteDto>>(utenteService.getUtenti(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UtenteDto>> addUtenti(List<UtenteDto> utenti) {
        return new ResponseEntity<List<UtenteDto>>(utenteService.addUtenti(utenti), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UtenteDto> updateUtente(UtenteDto utente) {
        return new ResponseEntity<UtenteDto>(utenteService.updateUtente(utente), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteUtente(Long id) {
        return new ResponseEntity<Boolean>(utenteService.deleteUtente(id), HttpStatus.OK);
    }

}
