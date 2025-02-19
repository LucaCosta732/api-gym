package it.lucacosta.gym.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lucacosta.gym.controller.AllenatoreController;
import it.lucacosta.gym.dto.request.AllenatoreRequest;
import it.lucacosta.gym.dto.response.AllenatoreResponse;
import it.lucacosta.gym.service.AllenatoreService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/allenatore")
@RequiredArgsConstructor
@Validated
public class AllenatoreControllerImpl implements AllenatoreController {

    private final AllenatoreService allenatoreService;

    @Override
    public ResponseEntity<AllenatoreResponse> getAllenatoreById(Long id) {
        return new ResponseEntity<AllenatoreResponse>(allenatoreService.getAllenatoreById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AllenatoreResponse>> getAllenatori(String name) {
        return new ResponseEntity<List<AllenatoreResponse>>(allenatoreService.getAllenatori(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AllenatoreResponse>> addAllenatori(List<AllenatoreRequest> allenatori) {
        return new ResponseEntity<List<AllenatoreResponse>>(allenatoreService.addAllenatori(allenatori), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AllenatoreResponse> updateAllenatore(Long id, AllenatoreRequest allenatore) {
        return new ResponseEntity<AllenatoreResponse>(allenatoreService.updateAllenatore(id, allenatore),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteAllenatore(Long id) {
        return new ResponseEntity<Boolean>(allenatoreService.deleteAllenatore(id), HttpStatus.OK);
    }

}
