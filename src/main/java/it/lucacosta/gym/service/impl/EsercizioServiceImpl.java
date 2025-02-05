package it.lucacosta.gym.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.request.EsercizioRequest;
import it.lucacosta.gym.dto.response.EsercizioResponse;
import it.lucacosta.gym.mapper.DtoMapper;
import it.lucacosta.gym.mapper.ModelMapper;
import it.lucacosta.gym.model.Esercizio;
import it.lucacosta.gym.repository.EsercizioRepository;
import it.lucacosta.gym.service.EsercizioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EsercizioServiceImpl implements EsercizioService {

    private final EsercizioRepository esercizioRepository;
    private final ModelMapper modelMapper;
    private final DtoMapper dtoMapper;

    @Override
    public EsercizioResponse getEsercizioById(Long id) {
        log.info("[START] - [EsercizioServiceImpl] - getEsercizioById - ID: {}", id);

        Esercizio esercizio = findActiveEsercizioById(id);

        log.info("[END] - [EsercizioServiceImpl] - getEsercizioById - ID: {} - Esercizio found", id);
        return dtoMapper.toResponse(esercizio);
    }

    @Override
    public List<EsercizioResponse> getEsercizi() {
        log.info("[START] - [EsercizioServiceImpl] - getEsercizi");

        List<Esercizio> esercizi = esercizioRepository.findAllByEliminatoFalse();

        log.info("[END] - [EsercizioServiceImpl] - getEsercizi - Found {} Esercizi", esercizi.size());
        return dtoMapper.toResponse_E(esercizi);
    }

    @Override
    public EsercizioResponse addEsercizio(EsercizioRequest esercizioRequest) {
        log.info("[START] - [EsercizioServiceImpl] - addEsercizio");
        Esercizio esercizioToSave = modelMapper.toModel(esercizioRequest);
        esercizioToSave.setId(null); 
        esercizioToSave.setEliminato(false); 

        Esercizio savedEsercizio = esercizioRepository.save(esercizioToSave);

        log.info("[END] - [EsercizioServiceImpl] - addEsercizio - Esercizio ID: {} created", savedEsercizio.getId());
        return dtoMapper.toResponse(savedEsercizio);
    }

    @Override
    public List<EsercizioResponse> addEsercizi(List<EsercizioRequest> eserciziRequests) {
        log.info("[START] - [EsercizioServiceImpl] - addEsercizi");

        if (eserciziRequests == null || eserciziRequests.isEmpty()) {
            log.warn("No Esercizi requests provided.");
            return List.of(); 
        }

        List<Esercizio> eserciziToSave = modelMapper.toModel_E(eserciziRequests);
        eserciziToSave.forEach(esercizio -> {
            esercizio.setId(null); 
            esercizio.setEliminato(false); 
        });

        List<Esercizio> savedEsercizi = esercizioRepository.saveAll(eserciziToSave);

        log.info("[END] - [EsercizioServiceImpl] - addEsercizi - Saved {} Esercizi", savedEsercizi.size());
        return dtoMapper.toResponse_E(savedEsercizi);
    }

    @Override
    public EsercizioResponse updateEsercizio(Long id, EsercizioRequest esercizioRequest) {
        log.info("[START] - [EsercizioServiceImpl] - updateEsercizio - ID: {}", id);

        Esercizio existingEsercizio = findActiveEsercizioById(id);

        modelMapper.updateModelFromDto(esercizioRequest, existingEsercizio); 
        existingEsercizio.setEliminato(false); 

        Esercizio updatedEsercizio = esercizioRepository.save(existingEsercizio);

        log.info("[END] - [EsercizioServiceImpl] - updateEsercizio - ID: {} - Esercizio updated", id);
        return dtoMapper.toResponse(updatedEsercizio);
    }

    @Override
    public Boolean deleteEsercizio(Long id) {
        log.info("[START] - [EsercizioServiceImpl] - deleteEsercizio - ID: {}", id);

        Esercizio esercizioToDelete = findActiveEsercizioById(id);
        esercizioToDelete.setEliminato(true); 
        esercizioRepository.save(esercizioToDelete);

        log.info("[END] - [EsercizioServiceImpl] - deleteEsercizio - ID: {} - Esercizio soft deleted", id);
        return true;
    }

    private Esercizio findActiveEsercizioById(Long id) {
        log.debug("Finding active Esercizio by ID: {}", id);
        Optional<Esercizio> esercizioOptional = esercizioRepository.findByIdAndEliminatoFalse(id);
        return esercizioOptional.orElseThrow(() -> {
            log.warn("Esercizio not found or not active for ID: {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Esercizio con id " + id + " non trovato o non attivo.");
        });
    }
}