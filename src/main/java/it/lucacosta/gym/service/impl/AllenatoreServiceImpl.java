package it.lucacosta.gym.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.request.AllenatoreRequest;
import it.lucacosta.gym.dto.response.AllenatoreResponse;
import it.lucacosta.gym.mapper.DtoMapper;
import it.lucacosta.gym.mapper.ModelMapper;
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
    private final ModelMapper modelMapper;
    private final DtoMapper dtoMapper;

    @Override
    public List<AllenatoreResponse> addAllenatori(List<AllenatoreRequest> allenatoreRequests) {
        log.info("[START] - [AllenatoreServiceImpl] - addAllenatori");

        List<Allenatore> allenatoriToSave = modelMapper.toModel_A(allenatoreRequests);
        allenatoriToSave.forEach(allenatore -> {
            allenatore.setId(null);
            allenatore.setEliminato(false);
        });

        List<Allenatore> savedAllenatori = allenatoreRepository.saveAll(allenatoriToSave);

        log.info("[END] - [AllenatoreServiceImpl] - addAllenatori - Salvato {} Allenatori", savedAllenatori.size());
        return dtoMapper.toResponse_A(savedAllenatori);
    }

    @Override
    public AllenatoreResponse updateAllenatore(Long id, AllenatoreRequest allenatoreRequest) {
        log.info("[START] - [AllenatoreServiceImpl] - updateAllenatore - ID: {}", id);

        Allenatore existingAllenatore = findActiveAllenatoreById(id);

        modelMapper.updateModelFromDto(allenatoreRequest, existingAllenatore);
        existingAllenatore.setEliminato(false);

        Allenatore updatedAllenatore = allenatoreRepository.save(existingAllenatore);

        log.info("[END] - [AllenatoreServiceImpl] - updateAllenatore - ID: {} - Aggiornato", id);
        return dtoMapper.toResponse(updatedAllenatore);
    }

    @Override
    public Boolean deleteAllenatore(Long id) {
        log.info("[START] - [AllenatoreServiceImpl] - deleteAllenatore - ID: {}", id);

        Allenatore allenatoreToDelete = findActiveAllenatoreById(id);

        allenatoreToDelete.setEliminato(true);
        allenatoreRepository.save(allenatoreToDelete);

        log.info("[END] - [AllenatoreServiceImpl] - deleteAllenatore - ID: {} - Allenatore eliminato", id);
        return true;
    }

    @Override
    public AllenatoreResponse getAllenatoreById(Long id) {
        log.info("[START] - [AllenatoreServiceImpl] - getAllenatoreById - ID: {}", id);

        Allenatore allenatore = findActiveAllenatoreById(id);

        log.info("[END] - [AllenatoreServiceImpl] - getAllenatoreById - ID: {} - Allenatore trovato", id);
        return dtoMapper.toResponse(allenatore);
    }

    @Override
    public List<AllenatoreResponse> getAllenatori(String name) {
        log.info("[START] - [AllenatoreServiceImpl] - getAllenatori - Nome: {}", name);

        List<Allenatore> allenatori;
        if (name == null || name.trim().isEmpty()) {
            log.debug("Trova Allenatori.");
            allenatori = allenatoreRepository.findAllByEliminatoFalse();
        } else {
            log.debug("Trova allenatori attivi con nome: {}", name);
            allenatori = allenatoreRepository.findAllByNomeContainsIgnoreCaseAndEliminatoFalse(name);
        }

        log.info("[END] - [AllenatoreServiceImpl] - getAllenatori - Found {} Allenatori", allenatori.size());
        return dtoMapper.toResponse_A(allenatori);
    }

    private Allenatore findActiveAllenatoreById(Long id) {
        log.debug("Trova Allenatore con ID: {}", id);
        Optional<Allenatore> allenatoreOptional = allenatoreRepository.findByIdAndEliminatoFalse(id);
        return allenatoreOptional.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Allenatore con ID " + id + " non trovato"));
    }
}