package it.lucacosta.gym.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lucacosta.gym.controller.AllenatoreController;
import it.lucacosta.gym.dto.AllenatoreDto;
import it.lucacosta.gym.service.AllenatoreService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/allenatore")
@RequiredArgsConstructor
public class AllenatoreControllerImpl implements AllenatoreController {

    private final AllenatoreService allenatoreService;

    @Override
    public ResponseEntity<AllenatoreDto> getUtenteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUtenteById'");
    }

    @Override
    public ResponseEntity<List<AllenatoreDto>> getUtenti(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUtenti'");
    }

    @Override
    public ResponseEntity<AllenatoreDto> addUtente(AllenatoreDto allenatore) {
        return new ResponseEntity<AllenatoreDto>(allenatoreService.addAllenatore(allenatore), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AllenatoreDto> updateUtente(AllenatoreDto allenatore) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUtente'");
    }

    @Override
    public ResponseEntity<Boolean> deleteUtente(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUtente'");
    }

    

}
