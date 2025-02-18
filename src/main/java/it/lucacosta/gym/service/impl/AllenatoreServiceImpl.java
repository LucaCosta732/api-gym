package it.lucacosta.gym.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.request.AllenatoreRequest;
import it.lucacosta.gym.dto.response.AllenatoreResponse;
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
    public List<AllenatoreResponse> addAllenatori(List<AllenatoreRequest> allenatoriRequest) {
        List<Allenatore> allenatori = allenatoreMapper.toModel(allenatoriRequest);
        allenatoreRepository.saveAll(allenatori);
        return allenatoreMapper.toDto(allenatori);
    }

    @Override
    public AllenatoreResponse updateAllenatore(Long id, AllenatoreRequest allenatoreRequest) {
        Allenatore allenatore = trovaAllenatoreAttivo(id);
        allenatoreMapper.updateModelFromDto(allenatoreRequest, allenatore);
        allenatoreRepository.save(allenatore);
        return allenatoreMapper.toDto(allenatore);
    }

    @Override
    public Boolean deleteAllenatore(Long id) {
        Allenatore allenatore = trovaAllenatoreAttivo(id);
        allenatore.setEliminato(true);
        allenatoreRepository.save(allenatore);
        return true;
    }

    @Override
    public AllenatoreResponse getAllenatoreById(Long id) {
        Allenatore allenatore = trovaAllenatoreAttivo(id);
        return allenatoreMapper.toDto(allenatore);
    }

    @Override
    public List<AllenatoreResponse> getAllenatori(String name) {
        List<Allenatore> allenatori;
        if (name == null || name.isBlank()) {
            allenatori = allenatoreRepository.findAllByEliminatoFalse();
        } else {
            allenatori = allenatoreRepository.findAllByNomeContainsIgnoreCaseAndEliminatoFalse(name);
        }
        return allenatoreMapper.toDto(allenatori);
    }

    private Allenatore trovaAllenatoreAttivo(Long id) {
        Optional<Allenatore> allenatore = allenatoreRepository.findByIdAndEliminatoFalse(id);
        // Se non esiste restituisce eccezione
        return allenatore.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Allenatore non trovato"));
    }
}
