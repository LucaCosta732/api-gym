package it.lucacosta.gym.service.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.AllenatoreDto;
import it.lucacosta.gym.mapper.AllenatoreMapper;
import it.lucacosta.gym.model.Allenatore;
import it.lucacosta.gym.repository.AllenatoreRepository;
import it.lucacosta.gym.service.AllenatoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AllenatoreServiceImpl implements AllenatoreService {

    private final AllenatoreRepository allenatoreRepository;
    private final AllenatoreMapper allenatoreMapper;

    @Override
    public AllenatoreDto addAllenatore(AllenatoreDto allenatore) {
    
        log.info("[START] - [AllenatoreServiceImpl] - addAllenatore");
    
        Allenatore a = allenatoreMapper.toModel(allenatore);
    
        try {
            allenatoreRepository.save(a);
        } catch (DataIntegrityViolationException e) {
            log.error("Error saving Allenatore due to data integrity violation (likely duplicate email): {}", e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Allenatore con questa email già esistente", 
                    e 
            );
        }
    
        log.info("[END] - [AllenatoreServiceImpl] - addAllenatore - Allenatore salvato: {}" , a);
    
        return allenatoreMapper.toDto(a);
    }

    @Override
    public AllenatoreDto updateAllenatore(AllenatoreDto allenatore) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAllenatore'");
    }

    @Override
    public Boolean deleteAllenatore(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllenatore'");
    }

    @Override
    public AllenatoreDto getAllenatoreById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllenatoreById'");
    }

    @Override
    public List<AllenatoreDto> getAllenatori(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllenatori'");
    }

}
