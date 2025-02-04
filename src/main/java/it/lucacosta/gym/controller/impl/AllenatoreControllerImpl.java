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
@RequestMapping("/v1/allenatore")
@RequiredArgsConstructor
public class AllenatoreControllerImpl implements AllenatoreController {

    private final AllenatoreService allenatoreService;

    @Override
    public ResponseEntity<AllenatoreDto> getAllenatoriById(Long id) {
        return new ResponseEntity<AllenatoreDto>(allenatoreService.getAllenatoreById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AllenatoreDto>> getAllenatori(String name) {
        return new ResponseEntity<List<AllenatoreDto>>(allenatoreService.getAllenatori(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AllenatoreDto>> addAllenatori(List<AllenatoreDto> allenatore) {
        return new ResponseEntity<List<AllenatoreDto>>(allenatoreService.addAllenatori(allenatore), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AllenatoreDto> updateAllenatore(AllenatoreDto allenatore) {
        return new ResponseEntity<AllenatoreDto>(allenatoreService.updateAllenatore(allenatore), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteAllenatore(Long id) {
        return new ResponseEntity<Boolean>(allenatoreService.deleteAllenatore(id), HttpStatus.OK);
    }
}
